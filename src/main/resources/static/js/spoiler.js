$(document).ready(function () {
    $('.spoiler-body').hide();
    $('.spoiler-title').click(function () {
        $(this).toggleClass('opened').toggleClass('closed').parent().prev().slideToggle();
        if ($(this).hasClass('opened')) {
            $(this).html('свернуть');
            $(this).prepend('<img src="../images/arrow_close.png" alt="close" style="margin-right:10px;" />');
        }
        else {
            $(this).html('развернуть');
            $(this).prepend('<img src="../images/arrow_open.png" alt="open" style="margin-right:10px;" />');
        }
    });
});