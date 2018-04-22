$(document).ready(function(){
    setupUI();
});


// State / DAta
var state = {
    author: null
};

function setAuthor(author) {
    state.author = author;
    $('#authorName').text(author.givenName + ' ' + author.familyName + ' (' + author.emailAddress + ')');
    $('#project').text(author.project.title + ' [' + 
    		author.project.publicationYear + '/' + author.project.publicationMonth + ']');
    $('#projectUrl').text(author.project.idUrl);
    $('#debugAuthorAll').text(JSON.stringify(author));
}


// API

function loadOrcidProfile(orcidId) {
    $.ajax({
        url: 'http://localhost:8080/orcid/profile/' + orcidId
    }).then(function(data) {
        setAuthor(data);
    });
}

// Button-Event
//$('#acceptAuthor').on('click', function (e) { //your awesome code here })

// UI

function setAuthorProfile(profile) {

}

function setupUI() {
    setupAuthorSearchUI();
    $('acceptAuthor').prop('disabled', true);
}

function setupAuthorSearchUI() {
    var orcidResults = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        // prefetch: '../data/films/post_1960.json',
        remote: {
            url: 'http://localhost:8080/orcid/search?q=%QUERY',
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
            loadOrcidProfile(data.path);
        });
}
