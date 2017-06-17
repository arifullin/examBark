function createNews(id) {
    $("#no_news").remove();
    var title = $("#news_title").val();
    var body = $("#news_body").val();
    var tags = $("#news_tags").val();
    var data = new FormData();
    jQuery.each(jQuery('#news_file')[0].files, function(i, file) {
        data.append('file'+(i+1), file);
    });
    data.append("title", title);
    data.append("body", body);
    data.append("tags", tags);
    $.ajax({
        url: "/project/"+id+"/new_news",
        type: "POST",
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        success: function (item) {
            $("#project_news").prepend(item);
            $("#news_title").val("");
            $("#news_body").val("");
            $("#news_tags").val("");
            $("#news_file").val("");
        }
    })
}
function createMyEvent(id) {
    $("#no_events").remove();
    var title = $("#header").val();
    var body = $("#event_description").val();
    var tags = $("#tags").val();
    var data = new FormData();
    jQuery.each(jQuery('#event_files')[0].files, function(i, file) {
        data.append('file'+(i+1), file);
    });
    var address = $("#address").val();
    var date = $("#date").val();
    var time1 = $("#time1").val();
    var time2 = $("#time2").val();
    data.append("header", title);
    data.append("description", body);
    data.append("tags", tags);
    data.append("time1", time1);
    data.append("time2", time2);
    data.append("address", address);
    data.append("date", date);
    $.ajax({
        url: "/project/"+id+"/new_event",
        type: "POST",
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        success: function (item) {
            $("#project_events").prepend(item);
            $("#header").val("");
            $("#event_description").val("");
            $("#tags").val("");
            $("#event_files").val("");
            $("#address").val("");
            $("#time1").val("");
            $("#time2").val("");
            $("#date").val("");
        }
    })
}
function deleteEvent(projectId,id) {
    $.ajax({
        url: "/project/"+projectId+"/delete_event",
        type: "POST",
        data: {
            eventId: id
        },
        success: function () {
            $("#pr_ev_"+id).remove();
        }
    })
}
function deleteNews(projectId,id) {
    $.ajax({
        url: "/project/"+projectId+"/delete_news",
        type: "POST",
        data: {
            newsId: id
        },
        success: function () {
            $("#pr_news_"+id).remove();
        }
    })
}