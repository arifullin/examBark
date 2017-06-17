/**
 * Created by Daniel Shchepetov on 26.05.2017.
 */
function certificateDone(user_id) {
    console.log('Ajax certificate request');


    $.ajax({
        type: 'POST',
        url: '/worker/profile/certificate_done',
        data: {userId: user_id},
        success: function () {
            $('#wrapper'+user_id).remove();
        },
        error: function () {
            console.log('error!');
        }
    });
}