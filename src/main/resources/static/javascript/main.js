$(document).ready(function(){
    setupUI();
});


// State / Data
var state = {
    author: null,
    project: null,
    files: []
};

function setAuthor(profile) {
    state.author = profile;
}

function setProject(project) {
    state.project = project;
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
        setContinueFromDataImportButtonTitle('Please wait - loading files from Github...');
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
        setFileListUI(data.entries);
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


function createFileTagDropdown(fileIndex) {
    var html = '<div class="btn-group" id="tag_toggle_' + fileIndex +'" data-index="'+fileIndex+'">';
    html += '<button  class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
    html += 'Ignore';
    html += '</button><div class="dropdown-menu">';
    html += '<a class="dropdown-item" style="cursor: pointer" name="input_data">Input Data</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="output_data">Output Data</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="software">Software</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="publication">Publication</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="documentation">Documentation</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="presentation">Presentation</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="intermediate">Intermediate</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="ignore">Ignore</a>';
    html += '</div></div>';

    return html;
}

function createPreservationDurationDropdown(fileIndex) {
    var html = '<div class="btn-group" id="preservation_duration_toggle_' + fileIndex +'" data-index="'+fileIndex+'">';
    html += '<button  class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
    html += 'Ignore';
    html += '</button><div class="dropdown-menu">';
    html += '<a class="dropdown-item" style="cursor: pointer" name="5">5 Years</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="10">10 Years</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="20">20 Years</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="50">50 Years</a>';
    html += '<a class="dropdown-item" style="cursor: pointer" name="ignore">Ignore</a>';
    html += '</div></div>';

    return html;
}

function setFileListUI(githubFiles) {
    $('#fileListTable').find('tbody > tr').remove();

    for(var i=0; i<githubFiles.length; i++) {
        var githubFile = githubFiles[i];

        var file = {
            name: githubFile.name,
            path: githubFile.path,
            size: githubFile.size,
            checksum: githubFile.sha,
            tag: null,
            preservation_duration: null,
        };

        state.files.push(file);

        var html = '<tr>';
        html += '<td><small>'+file.name+'</small></td>';
        html += '<td><small>'+file.path+'</small></td>';
        html += '<td style="text-align:center">'+createFileTagDropdown(i)+'</td>';
        html += '<td style="text-align:center">'+createPreservationDurationDropdown(i)+'</td>';
        html += '</tr>';

        $('#fileListTable').find('> tbody').append(html);

        $('#tag_toggle_'+ i + ' .dropdown-menu a').click(function(){
            var text = $(this).text();
            var tag= $(this).attr('name');
            var index = parseInt($(this).parents('.btn-group').attr('data-index'));

            if(tag === 'ignore') {
                tag = null;
            }

            window.state.files[index].tag = tag;
            $(this).parents('.btn-group').find('.dropdown-toggle').text(text);
        });

        $('#preservation_duration_toggle_'+ i + ' .dropdown-menu a').click(function(){
            var text = $(this).text();
            var duration= $(this).attr('name');
            var index = parseInt($(this).parents('.btn-group').attr('data-index'));

            if(duration === 'ignore') {
                duration = null;
            } else {
                duration = parseInt(duration);
            }

            window.state.files[index].preservation_duration = duration;
            $(this).parents('.btn-group').find('.dropdown-toggle').text(text);
        });
    }

    setContinueFromDataImportButtonTitle('Continue to PRESERVATION');
    $('#continueFromDataImportButton').removeClass('disabled').prop('disabled', false);
}

function setupUI() {
    setupDataImportUI();
}

function showPreservationTab() {
    $('#tab_preservation').removeClass('disabled').tab('show').addClass('disabled');
}

function showDMP(json_data) {
	
	$('#projectTitle').text(state.author.project.title);
	
	$('#txt_author').html(state.author.givenName + ' ' + 
			state.author.familyName + '<br><ul><li>'+ state.author.emailAddress + '</li><br><li>' +
			state.author.givenName + '</li></ul>');
	
	$('#txt_version').html(state.author.project.publicationDay + '.' + 
			state.author.project.publicationMonth + '.' + state.author.project.publicationYear);
	
	var outputFiles = $.grep(state.files, function(v) {
		return v.tag = 'output_data';
	});
	for(var i=0; i<outputFiles.length; i++) {
		if(i==0){
			$('#txt_output_list').find('> thead').append('<br><h3><i><b>Produced Data</b></i></h3>');
		}
		var outputFile = outputFiles[i];
		addFileToOutput(outputFile, '#txt_output_list');		
	}
	
	var inputFiles = $.grep(state.files, function(v) {
		return v.tag = 'input_data';
	});
	for(var i=0; i<inputFiles.length; i++) {
		if(i==0){
			$('#txt_input_list').find('> thead').append('<br><h3><i><b>Gathered Data</b></i></h3>');
		}
		var inputFile = inputFiles[i];
		addFileToOutput(inputFile, '#txt_input_list');		
	}
	
	
	$('#txt_json').text(json_data);
	
	$('#tab_dmp').removeClass('disabled').tab('show').addClass('disabled');
}

function addFileToOutput(file,id){
	var html = '<tr>';
    html += '<td><small>'+file.name+'</small></td>';
    html += '<td><small>size: '+file.size+'</small></td>';
    if(file.preservation_duration != null){
    	html += '<td><small>preserve: '+file.preservation_duration+' years</small></td>';
    }else{
    	html += '<td><small>do not preserve</small></td>';
    }
    html += '</tr><tr>';
    html += '<td><small>'+file.checksum+'</small></td></tr>';

    $(id).find('> tbody').append(html);
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

    $('#continueFromDataImportButton').click(function(e) {
        $(window).scrollTop(0);
        showPreservationTab();
    });
    
    $('#generateHtmlDMP').click(function(e) {
    	$(window).scrollTop(0);
    	//$.ajax({
    	//	url: '/dmpFile/getDMP/' + JSON.stringify(state)
    	//}).then(function(data) {
    	//	showDMP(data);
    	//});
    	showDMP('asdf');
    });
}

function setContinueFromDataImportButtonTitle(title) {
    $('#continueFromDataImportButton').text(title);
}

function clearUI() {
    $('#continueFromDataImportButton').addClass('disabled').prop('disabled', true);
    $('#orcidInfoCard').addClass('d-none');
    $('#profileInfoCard').addClass('d-none');

    $('#givenName').text('');
    $('#familyName').text('');
    $('#email').text('');
    $('#projectTitle').text('');
    $('#projectDOIUrl').text('');
    $('#publicationDate').text('');
    setContinueFromDataImportButtonTitle('Find valid Orcid Profile to continue');

    $('#rights').text('');
    $('#githubUrl').text('');
    $('#creators').text('');
    $('#type').text('');

    $('#fileListTable tbody > tr').remove();
}
