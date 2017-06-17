<#include "../layout.ftl">
<#import "/spring.ftl" as spring />

<@mainLayout header="Communities">

<!— Add Group —>
<form method="post" action="<@spring.url '/groups/create_community'/>" name="communityForm"
      enctype="multipart/form-data">

    <div class="group_add_block">
        <div class="group_add_photo">
        <#--  Когда добавите форму создания сообщества добавите сюда с этими атрибутами-->
            <@spring.formInput path="communityForm.avatar" attributes="class=\"font_group_add_photo visibility_collapse\""  fieldType="file"/>
            <@spring.showErrors "br"/>
            <label class="font_group_add_photo" for="avatar">Загрузить фото</label>
        </div>

        <div class="group_add_text_block">
            <@spring.formInput  path="communityForm.name"  attributes="required class=\"group_add_text_input font_placeholder_student_search\" placeholder=\"Введите название\""  fieldType="text" />
        <@spring.showErrors "br"/>
            <#if message??>
        ${message}
        </#if>


        </div>
        <div class=" flex_justify_end">
            <button type="submit" id="sendProject" class="add_group_button">
                Создать группу
            </button>
        </div>
    </div>
</form>
<!— Add Group End —>

<div class="main_block_with_flexbox">
    <#list communities as community>
        <div id="communities">
            <div class="event_block group_block flex_row">
                <div>
                    <a href="<@spring.url '/group/${community.id}'/>">
                        <#if community.avatarUrl??>
                            <img src="<@spring.url '/uploads/Community_Avatar/${community.avatarUrl}'/>"
                                 class="group_img" alt="img">
                        <#else>
                            <img src="<@spring.url '/images/square_ph.png'/>" class="group_img" alt="img">
                        </#if>
                    </a>
                </div>
                <div class="width-100-proc">
                    <div class="flex_row">
                        <a href="<@spring.url '/group/${community.id}'/>">
                            <p class="font_event_header">
                                <#if community.name??>
                            ${community.name}
                            </#if>
                            </p>
                        </a>
                    </div>
                    <hr>
                    <div class="main_block_with_flexbox">
                        <div class="flex_column">
                            <span class="font_mini_header">
                                <#if community.leader??>${community.leader.name} ${community.leader.surname}</#if>
                            </span>
                            <p class="font_events_text group_text text_overflow group_small_height">
                                <#if community.description??>${community.description}</#if>
                            </p>
                        </div>

                        <div class="flex_row">
                            <div class="font_group_participants">
                                <#if community.getUsersCount()??>
                            ${community.getUsersCount()}
                                </#if>
                            </div>
                            <img src="<@spring.url '/images/Events_Page_participants.png'/>"
                                 class="group_participants_img">
                        </div>
                    </div>
                    <div class="group_button flex_justify_end">
                        <button class="event_button font_button_checkbox_radio"
                                onclick="location.href='/group/${community.id}'">
                            Открыть
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>

<div class="group_button flex_justify_end">
    <button class="show_more_block_large" id="font_show_more_block" onclick="loadMoreGroups(); return false">
        Показать больше сообществ
    </button>
</div>
<#--<!— Add Group —>
<div class="group_add_block">
    <form method="post" action="/main/create_community" name="communityForm" enctype="multipart/form-data">
        <@spring.formInput "communityForm.avatar" "" "file"/>
        <@spring.showErrors "<br/>"/>
        <@spring.formInput "communityForm.name"/>
        <@spring.showErrors "<br/>"/>
        <#if message??>
        ${message}
        </#if>

        <button type="submit" id="sendProject">Создать Группу</button>
    </form>
    </form>

</div>
<!— Add Group End —>-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script>

    function loadMoreGroups() {
        $.ajax({
            url: "/groups/load_more",
            type: "GET",
            success: function (groups) {
                $("#communities").append(groups);
            }
        });
    }

</script>


</@mainLayout>