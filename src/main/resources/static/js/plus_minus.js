$(document).ready(function () {
    $('#minus').click(function () {
        console.log('minus');
        var $input = $('#count'); // by id is better than parent and bla bla bla...
        var count = parseInt($input.val()) - 1;
        count = count < 1 ? 1 : count;
        $input.val(count);
        $input.change();
        return false;
    });
    $('#plus').click(function () {
        var $input = $('#count');
        var count = parseInt($input.val());
        if (count === 5) {
            return false;
        }
        $input.val(count + 1);

        $input.change();
        return false;
    });
});
