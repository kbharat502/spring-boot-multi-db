# spring-boot-multi-db
Spring boot 3 application with multiple databases.

# Usage
**Compile:** `mvn clean package`

**Run:**  `mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=contacth2,val-prop"`

## Valid Profile Combinations

| Combination | Profiles to use |
| -------------- | ---------- |
| **Contacts from embedded H2 database with validation messages from properties files** | `contacth2,val-prop` |
| **Contacts from postgresql database with validation messages from properties files** | `contactpg,val-prop` |
| **Contacts and validation messages from embedded H2 database** | `contacth2,val-db,valmsgh2` |
| **Contacts and validation messages from postgresql database** | `contactpg,val-db,valmsgpg` |
| **Contacts from embedded H2 database with validation messages from postgresql database** | `contacth2,val-db,valmsgpg` |
| **Contacts from postgresql database with validation messages from embedded H2 database** | `contactpg,val-db,valmsgh2` |

## Creating the postgresql database.
Use the scripts located in `src/main/resources/sql` to build the database(s).

| **For** | **Schema Name** | **Script Name** |
| ------- | --------------- | --------------- |
| Contacts Schema | `emp-dept` | `schema-contacts.sql` |
| Contacts Data | `emp-dept` | `data-contacts.sql` |
| Validations Schema | `val_msg` | `schema-messages.sql` |
| Validations Data | `val_msg` | `data-messages.sql` |
