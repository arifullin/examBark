package ru.kpfu.itis.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Safin Ramil on 03.06.17
 * Safin.Ramil@ordotrans.ru
 */
public class UploadUtils {

    // BASE PATHS
    public static final String UPLOAD_NEWS_PATH = "news";
    public static final String UPLOAD_EVENTS_PATH = "events";
    public static final String UPLOAD_DOCUMENTS_PATH = "documents";
    public static final String UPLOAD_AVATARS_PATH = "avatars";

    // SUB PATHS
    public static final String PROJECTS = "projects";
    public static final String COMMUNITIES = "communities";
    public static final String USERS = "users";

    // FULL PATHS
    public static final String UPLOAD_PROJECTS_PATH = Paths.get(UPLOAD_AVATARS_PATH, PROJECTS).toString();
    public static final String UPLOAD_COMMUNITIES_PATH = Paths.get(UPLOAD_AVATARS_PATH, COMMUNITIES).toString();
    public static final String UPLOAD_USERS_PATH = Paths.get(UPLOAD_AVATARS_PATH, USERS).toString();

    // ARRAY OF PATHS TO BE PRESENT
    public static final String[] PATHS = {
            UPLOAD_NEWS_PATH,
            UPLOAD_EVENTS_PATH,
            UPLOAD_DOCUMENTS_PATH,
            UPLOAD_AVATARS_PATH,
            UPLOAD_PROJECTS_PATH,
            UPLOAD_COMMUNITIES_PATH,
            UPLOAD_USERS_PATH
    };


    // PATHS
    public static final Path NEWS_PATH = Paths.get(UPLOAD_NEWS_PATH);
    public static final Path EVENTS_PATH = Paths.get(UPLOAD_EVENTS_PATH);
    public static final Path DOCUMENTS_PATH = Paths.get(UPLOAD_DOCUMENTS_PATH);
    public static final Path PROJECTS_PATH = Paths.get(UPLOAD_AVATARS_PATH, PROJECTS);
    public static final Path COMMUNITIES_PATH = Paths.get(UPLOAD_AVATARS_PATH, COMMUNITIES);
    public static final Path USERS_PATH = Paths.get(UPLOAD_AVATARS_PATH, USERS);

}
