package database;

import static database.Constants.Tables.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class SQLTableCreationFactory {

    public String getCreateSQLForTable(String table) {
        switch (table) {
            case ACCOUNT:
                return "CREATE TABLE IF NOT EXISTS `account` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `amount` DOUBLE NOT NULL DEFAULT 0," +
                        "  `client_id` INT NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  CONSTRAINT `fk_account_client`" +
                        "    FOREIGN KEY (`client_id`)" +
                        "    REFERENCES `client` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  UNIQUE INDEX `idaccount_UNIQUE` (`id` ASC)," +
                        "  INDEX `fk_account_client_idx` (`client_id` ASC));";

            case USER:
                return "CREATE TABLE IF NOT EXISTS `user` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `username` VARCHAR(45) NOT NULL," +
                        "  `password` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `iduser_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `username_UNIQUE` (`username` ASC));";

            case ROLE:
                return "  CREATE TABLE IF NOT EXISTS role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX role_UNIQUE (role ASC));";

            case RIGHT:
                return "  CREATE TABLE IF NOT EXISTS `right` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `right` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `right_UNIQUE` (`right` ASC));";

            case CLIENT:
                return "CREATE TABLE IF NOT EXISTS `client` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `name` VARCHAR(45) NOT NULL," +
                        "  `address` VARCHAR(60) NOT NULL," +
                        "  `cnp` VARCHAR(20) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                        "  UNIQUE INDEX `cnp_UNIQUE` (`cnp` ASC));";

            case ROLE_RIGHT:
                return "  CREATE TABLE IF NOT EXISTS role_right (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  role_id INT NOT NULL," +
                        "  right_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  INDEX right_id_idx (right_id ASC)," +
                        "  CONSTRAINT role_id" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT right_id" +
                        "    FOREIGN KEY (right_id)" +
                        "    REFERENCES `right` (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";

            case USER_ROLE:
                return "\tCREATE TABLE IF NOT EXISTS user_role (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  user_id INT NOT NULL," +
                        "  role_id INT NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  INDEX user_id_idx (user_id ASC)," +
                        "  INDEX role_id_idx (role_id ASC)," +
                        "  CONSTRAINT user_fkid" +
                        "    FOREIGN KEY (user_id)" +
                        "    REFERENCES user (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT role_fkid" +
                        "    FOREIGN KEY (role_id)" +
                        "    REFERENCES role (id)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE);";
            case TRANSFER :
                return "CREATE TABLE IF NOT EXISTS `transfer` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `amount` DOUBLE NOT NULL," +
                        "  `date` INT NOT NULL," +
                        "  `user_id` INT NOT NULL," +
                        "  `sender` INT NOT NULL," +
                        "  `receiver` INT NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  CONSTRAINT `fk_transfer_account1`" +
                        "    FOREIGN KEY (`sender`)" +
                        "    REFERENCES `account` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT `fk_transfer_user1`" +
                        "    FOREIGN KEY (`user_id`)" +
                        "    REFERENCES `user` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT `fk_transfer_account2`" +
                        "    FOREIGN KEY (`receiver`)" +
                        "    REFERENCES `account` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  UNIQUE INDEX `idtransfer_UNIQUE` (`id` ASC)," +
                        "  INDEX `fk_transfer_account1_idx` (`sender` ASC)," +
                        "  INDEX `fk_transfer_user1_idx` (`user_id` ASC)," +
                        "  INDEX `fk_transfer_account2_idx` (`receiver` ASC));";

            case BILL :
                return "CREATE TABLE IF NOT EXISTS `bill` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `description` VARCHAR(100) NOT NULL," +
                        "  `amount` DOUBLE NOT NULL," +
                        "  `date` INT NOT NULL," +
                        "  `user_id` INT NOT NULL," +
                        "  `account_id` INT NOT NULL," +
                        "  `client_id` INT NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  CONSTRAINT `fk_bill_user1`" +
                        "    FOREIGN KEY (`user_id`)" +
                        "    REFERENCES `user` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT `fk_bill_account1`" +
                        "    FOREIGN KEY (`account_id`)" +
                        "    REFERENCES `account` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  CONSTRAINT `fk_bill_client1`" +
                        "    FOREIGN KEY (`client_id`)" +
                        "    REFERENCES `client` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  UNIQUE INDEX `idbill_UNIQUE` (`id` ASC)," +
                        "  INDEX `fk_bill_user1_idx` (`user_id` ASC)," +
                        "  INDEX `fk_bill_account1_idx` (`account_id` ASC)," +
                        "  INDEX `fk_bill_client1_idx` (`client_id` ASC));";
            case ACTIVITY :
                return "CREATE TABLE IF NOT EXISTS `activity` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `date` INT NOT NULL," +
                        "  `user_id` INT NOT NULL," +
                        "  `description` VARCHAR(100) NOT NULL," +
                        "  PRIMARY KEY (`id`)," +
                        "  CONSTRAINT `fk_activity_user1`" +
                        "    FOREIGN KEY (`user_id`)" +
                        "    REFERENCES `user` (`id`)" +
                        "    ON DELETE CASCADE" +
                        "    ON UPDATE CASCADE," +
                        "  UNIQUE INDEX `idactivity_UNIQUE` (`id` ASC)," +
                        "  INDEX `fk_activity_user1_idx` (`user_id` ASC));";

            default:
                return "";

        }
    }

}
