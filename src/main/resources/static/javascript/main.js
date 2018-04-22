$(document).ready(function(){
    setupUI();
});


// State / Data
var state = {
    author: null,
    project: null,
    files: null
};

function setAuthor(profile) {
    state.author = profile;
}

function setProject(project) {
    state.project = project;
}

function setFiles(files) {
    state.files = files;
}

// API

function loadOrcidProfile(orcidId) {
    $.ajax({
        url: '/orcid/profile/' + orcidId
    }).then(function(data) {
        setAuthor(data);
        setAuthorOrcidInfoUI(data);

        if(data.project.id) {
            loadZenodoProject(data.project.id)
        }
    });
}


function loadZenodoProject(doi) {
    $.ajax({
        url: '/oai_pmh/record/?type=doi&identifier=' + doi
    }).then(function(data) {
        setProject(data);
        setProjectInfoUI(data);
        loadGithubFiles(data.githubUrl);
    });
}


function loadGithubFiles(githubUrl) {
    var urlParts = githubUrl.split('/');

    var githubUser = urlParts[3];
    var githubRepository = urlParts[4];

    var apiUrl = '/github/owner/'+githubUser+'/repository/'+githubRepository;

    if(urlParts.length == 7) {
        apiUrl += '?ref=' + urlParts[6];
    }

    $.ajax({
        url: apiUrl
    }).then(function(data) {
        setFiles(data.entries);
        $('#continueFromDataImportButton').removeClass('disabled');
    });
}

// UI

function setAuthorOrcidInfoUI(profile) {
    $('#givenName').text(profile.givenName);
    $('#familyName').text(profile.familyName);
    $('#email').text(profile.emailAddress);

    $('#projectTitle').text(profile.project.title);
    $('#projectDOIUrl').text(profile.project.idUrl).attr("href", profile.project.idUrl);

    if(profile.project.publicationYear) {
        $('#publicationDate').text(profile.project.publicationYear + '/' + profile.project.publicationMonth + '/' +profile.project.publicationDay);
    } else {
        $('#publicationDate').text('');
    }


    $('#orcidInfoCard').removeClass('d-none');
}


function setProjectInfoUI(project) {
    $('#rights').text(project.rights);
    $('#githubUrl').text(project.githubUrl).attr("href", project.githubUrl);

    if(project.creators) {
        $('#creators').text(project.creators.join(', '));
    }

    if(project.types) {
        for(var i=0; i<project.types.length; i++) {
            var type = project.types[i];
            if(!type.startsWith('info:')) {
                $('#type').text(type);
                break;
            }
        }
    }

    $('#projectInfoCard').removeClass('d-none');
}

function setupUI() {
    setupDataImportUI();
}

function showPreservationTab() {
    $('#tab_preservation').removeClass('disabled').tab('show').addClass('disabled');
}

function setupDataImportUI() {
    var orcidResults = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: '/orcid/search?q=%QUERY',
            wildcard: '%QUERY',
            filter: function (data) {
                return data.results;
            }
        }
    });

    $('#author_search').typeahead(
        {
            minLength: 3,

        },
        {
            name: 'orcid-results',
            display: 'value',
            source: orcidResults,
            templates: {
                empty: "No orcid user found",
                suggestion: function(data) {
                    return '<p>' + data.path + '</p>';
                }
            }
        }).on('typeahead:selected', function (obj, data) {
            clearUI();
            loadOrcidProfile(data.path);
        });

    $('#continueFromDataImportButton').click(function() {
        showPreservationTab();
    });
}

function clearUI() {
    $('#continueFromDataImportButton').addClass('disabled');
    $('#orcidInfoCard').addClass('d-none');
    $('#profileInfoCard').addClass('d-none');

    $('#givenName').text('');
    $('#familyName').text('');
    $('#email').text('');
    $('#projectTitle').text('');
    $('#projectDOIUrl').text('');
    $('#publicationDate').text('');

    $('#rights').text('');
    $('#githubUrl').text('');
    $('#creators').text('');
    $('#type').text('');
}
