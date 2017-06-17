<#include "../layout.ftl">
<#import "/spring.ftl" as spring />
<@mainLayout header="">
    <#if message??>
    ${message}
    </#if>
<#--<form method="post" action="/projects/create" name="projectForm" enctype="multipart/form-data">-->
    <#--<@spring.formInput "projectForm.avatar" "" "file"/>-->
    <#--<@spring.showErrors "<br/>"/>-->
    <#--<@spring.formInput "projectForm.name"/>-->
    <#--<@spring.showErrors "<br/>"/>-->
    <#--<button type="submit" id="sendProject">Создать проект</button>-->
<#--</form>-->


<form method="post" action="<@spring.url '/projects/create'/>" name="projectForm" enctype="multipart/form-data">

<div class="group_add_block">
    <div class="group_add_photo">
        <@spring.formInput "projectForm.avatar" "class=\"font_group_add_photo visibility_collapse\" " "file"/>
        <@spring.showErrors "<br/>"/>
        <label class="font_group_add_photo" for="avatar">Загрузить фото</label>
    </div>

    <div class="group_add_text_block">
        <@spring.formInput  path="projectForm.name"  attributes="required class=\"group_add_text_input font_placeholder_student_search\" placeholder=\"Введите название\""  fieldType="text" />
        <@spring.showErrors "<br/>"/>

    </div>
    <div class=" flex_justify_end">
        <button type="submit" id="sendProject" class="add_group_button">
            Создать проект
        </button>
    </div>
</div>
</form>

<div class="main_block_with_flexbox">




    <#list projects as project>
        <div id="projects">
            <div class="event_block group_block flex_row">
                <div>
                    <#if project.avatarUrl??>
                        <img src="<@spring.url '/uploads/Project_Avatar/${project.avatarUrl}'/>" class="group_img" alt="img">
                        <#else>
                            <img src="<@spring.url '/images/square_ph.png'/>" class="group_img" alt="img">
                    </#if>
                </div>
                <div class="width-100-proc">
                    <div class="flex_row">
                        <p class="font_event_header">
                        ${project.projectName}
                        </p>
                    </div>
                    <hr>
                    <div class="main_block_with_flexbox">
                        <div class="flex_column">
                            <span class="font_mini_header">
                            <#if project.leader??>${project.leader.name} ${project.leader.surname}</#if>
                            </span>
                            <p class="font_events_text group_text text_overflow group_small_height">
                            <#if project.description??>${project.description}</#if>
                            </p>
                        </div>
                        <div class="flex_row">
                            <div class="font_group_participants">
                            ${project.getUsersCount()}
                            </div>
                            <img src="<@spring.url '/images/Events_Page_participants.png'/>" class="group_participants_img">
                        </div>
                    </div>
                    <div class="group_button flex_justify_end">
                        <button class="event_button font_button_checkbox_radio" onclick="location.href='/project/${project.id}'">
                            Открыть
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>

<div class="group_button flex_justify_end">
    <button class="show_more_block_large" id="font_show_more_block" onclick="loadMoreProjects()">
        Показать больше проектов
    </button>

<#--<div class="show_more_block" id="font_show_more_block" >-->
<#--<a href="#">Показать больше</a>-->
<#--</div>-->
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script>

    function loadMoreProjects() {
        $.ajax({
            url: "/projects/load_more",
            type: "GET",
            success: function (project) {
                $("#projects").append(project);
            }
        });
    }

</script>
</@mainLayout>
