/**
 * Created by Daniel Shchepetov on 21.05.2017.
 */
$('input:checkbox').change(function () {

    var tagIds = [];
    $("input:checkbox[name=tag]:checked").each(function () {
        tagIds.push($(this).val());
    });

    $.ajax({
        type: 'POST',
        url: '/news/load_news',
        data: {tagIds: tagIds},
        success: function (news) {
            $("#wrapper").html("");
            $('#wrapper').html(news);
        },
        error: function () {
            alert("Sorry");
        }
    });
});