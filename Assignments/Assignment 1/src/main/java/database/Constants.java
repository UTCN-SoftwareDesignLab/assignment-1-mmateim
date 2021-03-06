package database;

import java.util.*;

import static database.Constants.Rights.*;
import static database.Constants.Roles.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class Constants {

    public static class Controller{
        public static final String MAINC_CREATE_ACC = "MainC_CreateAcc";
        public static final String MAINC_LOG_ACC = "MainC_LogAcc";
        public static final String LOGINC_REG_SUCC = "LoginC_RegSucc";
        public static final String ADMINACT_CLIENT = "AdminAct_Client";
        public static final String CLIENT_INFO_BACK = "ClientInfo_Back";
        public static final String CLIENT_INFO_ADVANCED = "ClientInfo_Advanced";
        public static final String CLIENT_INFO_ADVANCED_ACCOUNTS = "ClientInfo_Advanced_Accounts";
        public static final String ADMINACT_ADMIN = "AdminAct_Admin";
        public static final String CRUD_BACK = "CRUD_Back";
        public static final String ADMIN_PASS = "parola";
    }

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String ACCOUNT = "account";
        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";
        public static final String CLIENT = "client";
        public static final String TRANSFER = "transfer";
        public static final String BILL = "bill";
        public static final String ACTIVITY = "activity";
        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE, CLIENT, ACCOUNT, TRANSFER, BILL, ACTIVITY};
    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String EMPLOYEE = "employee";

        public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE};
    }

    public static class Rights {
        public static final String CREATE_USER = "create_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";

        public static final String CREATE_ACCOUNT = "create_account";
        public static final String DELETE_ACCOUNT = "delete_account";
        public static final String UPDATE_ACCOUNT = "update_account";

        public static final String ADD_CLIENT_INFO = "add_client_info";
        public static final String UPDATE_CLIENT_INFO = "update_client_info";
        public static final String DELETE_CLIENT_INFO = "delete_client_info";

        public static final String[] RIGHTS = new String[]{CREATE_USER, DELETE_USER, UPDATE_USER, CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT, ADD_CLIENT_INFO, UPDATE_CLIENT_INFO, DELETE_CLIENT_INFO};
    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(RIGHTS));

        ROLES_RIGHTS.get(EMPLOYEE).addAll(Arrays.asList(CREATE_ACCOUNT, DELETE_ACCOUNT, UPDATE_ACCOUNT, ADD_CLIENT_INFO, UPDATE_CLIENT_INFO, DELETE_CLIENT_INFO));

        return ROLES_RIGHTS;
    }

}
