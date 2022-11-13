package pl.akademiaqa.url;

public class ClickUpUrl {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAMS = "/team";
    private static final String SPACES = "/space";
    private static final String LIST = "/list";
    private static final String TASK = "/task";


    //team/36799494/space
    public static String getBaseUrl() {

        return BASE_URL;
    }

    public static String getTeamsUrl() {
        return TEAMS;
    }

    public static String getTeamUrl(String teamId) {
        return TEAMS + "/" + teamId;
    }

    //    public static String getSpacesUrl(String teamId) {return BASE_URL+TEAMS+"/"+teamId+SPACES;}
    public static String getSpacesUrl(String teamId) {
        return getTeamUrl(teamId) + SPACES;
    }

    public static String getSpaceUrl(String spaceId) {
        return SPACES + "/" + spaceId;
    }

    public static String getListsUrl(String spaceId) {
        return getSpaceUrl(spaceId) + LIST;
    }

    public static String getListUrl(String listId) {
        return LIST + "/" + listId;
    }


    public static String getTasksUrl(String listId) {
        return getListUrl(listId) + TASK;
    }

    public static String getTaskUrl(String taskId) {
        return TASK + "/" + taskId;
    }


}
