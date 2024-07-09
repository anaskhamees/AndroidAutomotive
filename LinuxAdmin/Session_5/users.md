# Admin Linux



### 1. Users

- Normal user 1000 -40K
- System user 1-199
- Service user 200-999

#### 1.1. Normal Users (UID 1000-40,000)

Normal users are standard users who interact with the system for everyday tasks such as running applications, accessing files, and performing regular activities.

#### Steps to Create a Normal User:

1. **Add User (`adduser` or `useradd`):**

   Use the `adduser` command, which is a higher-level script that provides a more user-friendly interface for adding users compared to `useradd`. It sets up the user's home directory, default shell, and other configurations.

   ```bash
   sudo adduser username
   ```

   Replace `username` with the desired username for the new user.

   **Example:**

   ```bash
   sudo adduser john
   ```

   This command prompts you to set a password and fill in optional user information like full name, phone number, etc.

   #### 1.1.1. `adduser`

   - **Purpose:** `adduser` is a higher-level command designed for ease of use and user interaction. It automates several steps in user creation, such as setting up the home directory, copying configuration files, and prompting the administrator for additional information like the user's full name, phone number, etc.

   - **Usage:**

     ```bash
     sudo adduser username
     ```

     This command prompts the administrator to set a password and fill in optional user information interactively.

   - **Features:**

     - Creates a home directory for the user by default (`/home/username`).
     - Copies default configuration files from `/etc/skel` to the user's home directory.
     - Prompts for additional user information (full name, phone number, etc.).

   - **Suitability:** `adduser` is recommended for creating normal user accounts where interactive setup and additional user information are desired.

   #### 1.1.2. `useradd`

   - **Purpose:** `useradd` is a lower-level command that directly adds a user to the system with minimal interaction. It is more suitable for scripting and automated user creation processes.

   - **Usage:**

     ```bash
     sudo useradd options username
     ```

     The `options` can include various flags to specify user attributes such as UID, home directory path, login shell, etc.

   - **Features:**

     - Does not create a home directory by default (`-M` option needed to prevent home directory creation).
     - Does not copy default configuration files.
     - Typically used with command-line options to specify user attributes.

   - **Suitability:** `useradd` is suitable for creating users in scripts or automated processes where minimal interaction and specific user attributes need to be set programmatically.

   ###  Differences:

   - **Interactivity:** `adduser` is interactive, prompting for user input during account setup, while `useradd` requires command-line options for configuration.
   - **Automation:** `useradd` is more suitable for automated user creation scripts due to its non-interactive nature and ability to specify all user attributes via options.
   - **Default Behavior:** `adduser` sets up default configurations like creating a home directory and copying configuration files, which `useradd` does not do unless specified.

   ### Choosing Between `adduser` and `useradd`:

   - Use `adduser` for creating normal user accounts interactively with default configurations and optional additional user information.
   - Use `useradd` for automated processes or when specific user attributes need to be set explicitly via command-line options.

2. **Set Password (`passwd`):**

   After creating the user, set a password for the account using `passwd`.

   ```bash
   sudo passwd username
   ```

   **Example:**

   ```bash
   sudo passwd john
   ```

   Follow the prompts to set and confirm the new password.

3. **Verify User Creation:**

   You can verify that the user has been successfully created by checking the `/etc/passwd` file or using the `id` command.

   ```bash
   id username
   ```

   **Example:**

   ```bash
   id john
   ```

   This command displays information about the user, including their UID (typically starting from 1000), primary group, and supplementary groups.

#### 1.2. System Users (UID 1-199)

System users are used for internal system processes and daemons that run services or perform administrative tasks. They are assigned low-numbered UIDs typically ranging from 1 to 199.

#### Steps to Create a System User:

1. **Add User (`useradd`):**

   Use the `useradd` command to add a system user. You specify the UID manually for system users to ensure they fall within the lower range.

   ```bash
   sudo useradd -r -s /sbin/nologin -M -u uid username
   ```

   - `-r`: Creates a system user.
   - `-s /sbin/nologin`: Sets the shell to `/sbin/nologin` to prevent interactive login.
   - `-M`: Prevents creation of a home directory.
   - `-u uid`: Specifies the UID for the user.

   **Example:**

   ```bash
   sudo useradd -r -s /sbin/nologin -M -u 1001 johnsys
   ```

   This command creates a system user named `johnsys` with UID 1001.

2. **Verify User Creation:**

   Verify the creation of the system user using the `id` command.

   ```bash
   id username
   ```

   **Example:**

   ```bash
   id johnsys
   ```

   Ensure that the user `johnsys` has been created with the specified UID.

### 3. Service Users (UID 200-999)

Service users are similar to system users but are typically assigned UIDs ranging from 200 to 999. They are used for specific services and applications that require their own user accounts for security and permission management purposes.

#### Steps to Create a Service User:

1. **Add User (`useradd`):**

   Use the `useradd` command to add a service user, specifying the UID manually within the designated range.

   ```bash
   sudo useradd -r -s /sbin/nologin -M -u uid username
   ```

   - `-r`: Creates a system user.
   - `-s /sbin/nologin`: Sets the shell to `/sbin/nologin` to prevent interactive login.
   - `-M`: Prevents creation of a home directory.
   - `-u uid`: Specifies the UID for the user.

   **Example:**

   ```bash
   sudo useradd -r -s /sbin/nologin -M -u 500 appuser
   ```

   This command creates a service user named `appuser` with UID 500.

2. **Verify User Creation:**

   Verify the creation of the service user using the `id` command.

   ```bash
   id username
   ```

   **Example:**

   ```bash
   id appuser
   ```

   Confirm that the user `appuser` has been created with the specified UID.

### Additional Commands for User Management:

- **Modify User (`usermod`):** Modify user account attributes such as UID, home directory, shell, etc.

  ```bash
  sudo usermod -u new_uid username
  ```

- **Delete User (`userdel`):** Remove a user account and optionally remove associated files.

  ```bash
  sudo userdel -r username
  ```

- **Manage Groups (`groupadd`, `groupmod`, `groupdel`):** Create, modify, and delete user groups.



#### - Groups:

- **Group:** A group is a collection of users who share the same access permissions to files and directories. Groups simplify access management by allowing permissions to be set for multiple users at once.
- **Group ID (GID):** Each group has a numeric identifier, the Group ID (GID), which helps the system manage and associate users with specific groups.

### Primary and Secondary Groups:

#### Primary Group:

- **Primary Group:** When a user account is created, it is assigned a primary group. The primary group is specified in the `/etc/passwd` file and has the same name as the username by default.
- **Purpose:** The primary group is the default group assigned to files and directories created by the user. It determines the group ownership of new files and defines initial group permissions for the user.

#### Secondary Groups:

- **Secondary Groups:** Users can belong to multiple groups besides their primary group. These additional groups are known as secondary or supplementary groups.
- **Usage:** Secondary groups allow users to access shared resources and files that require group permissions beyond their primary group.

### Switching or Logging in as Different Users:

#### 1. Using `su` (Substitute User) Command:

- **Purpose:** `su` allows you to switch to another user account, either temporarily or permanently.

- **Syntax:**

  ```bash
  su username
  ```

  Replace `username` with the name of the user account you want to switch to.

- **Example:**

  ```bash
  su john
  ```

  This command prompts for `john`'s password and, if successful, switches the current shell session to `john`'s environment.

- **Root Access:** If you run `su` without specifying a username, it defaults to `root`, prompting for the root password to gain administrative privileges.

#### 2. Using `sudo` (Superuser Do):

- **Purpose:** `sudo` allows authorized users to execute commands as another user, typically the superuser (root).

- **Syntax:**

  ```bash
  sudo -u username command
  ```

  Replace `username` with the target user and `command` with the command you want to execute.

- **Example:**

  ```bash
  sudo -u john ls /home/john
  ```

  This command runs `ls /home/john` as user `john`, assuming you have the appropriate `sudo` privileges.

- **Password Prompt:** `sudo` prompts for the current user's password (not the target user's password) to verify authorization. 

- To know how much user can not login in to it

  ```bash
  cat /etc/passwd |grep -v /bin/bash |wc -l
  ```

- To know the user we can login in to it

  ```bash
  cat /etc/passwd | grep /bin/bash | wc -l
  ```

  >- `cat /etc/passwd`: Outputs the contents of the `/etc/passwd` file.
  >
  >- `grep /bin/bash`: Filters out lines that contain `/bin/bash`, showing only users whose default shell is `/bin/bash`.
  >
  >- `wc -l`: Counts the number of lines returned by `grep`, which corresponds to the number of users with `/bin/bash` as their shell.

- To know the user ID who can i login to it

  ```bash
  cat /etc/passwd | grep /bin/bash 
  ```

  

### Summary:

- **Users:** Accounts used by individuals or processes to interact with the system.

- **Groups:** Collections of users sharing common access permissions.

- **Primary Group:** Default group assigned to a user, used for ownership of files and initial group permissions.

- **Secondary Groups:** Additional groups a user can belong to for accessing shared resources.

- **Switching Users:** `su` command for temporary user switching, `sudo` for executing commands as another user.

  



#### Groups:

- **Group:** A group is a collection of users who share the same access permissions to files and directories. Groups simplify access management by allowing permissions to be set for multiple users at once.
- **Group ID (GID):** Each group has a numeric identifier, the Group ID (GID), which helps the system manage and associate users with specific groups.

### Primary and Secondary Groups:

#### Primary Group:

- **Primary Group:** When a user account is created, it is assigned a primary group. The primary group is specified in the `/etc/passwd` file and has the same name as the username by default.
- **Purpose:** The primary group is the default group assigned to files and directories created by the user. It determines the group ownership of new files and defines initial group permissions for the user.

#### Secondary Groups:

- **Secondary Groups:** Users can belong to multiple groups besides their primary group. These additional groups are known as secondary or supplementary groups.
- **Usage:** Secondary groups allow users to access shared resources and files that require group permissions beyond their primary group.

### Switching or Logging in as Different Users:

#### 1. Using `su` (Substitute User) Command:

- **Purpose:** `su` allows you to switch to another user account, either temporarily or permanently.

- **Syntax:**

  ```bash
  su username
  ```

  Replace `username` with the name of the user account you want to switch to.

- **Example:**

  ```bash
  su john
  ```

  This command prompts for `john`'s password and, if successful, switches the current shell session to `john`'s environment.

- **Root Access:** If you run `su` without specifying a username, it defaults to `root`, prompting for the root password to gain administrative privileges.

#### 2. Using `sudo` (Superuser Do):

- **Purpose:** `sudo` allows authorized users to execute commands as another user, typically the superuser (root).

- **Syntax:**

  ```bash
  sudo -u username command
  ```

  Replace `username` with the target user and `command` with the command you want to execute.

- **Example:**

  ```bash
  sudo -u john ls /home/john
  ```

  This command runs `ls /home/john` as user `john`, assuming you have the appropriate `sudo` privileges.

- **Password Prompt:** `sudo` prompts for the current user's password (not the target user's password) to verify authorization.

### Summary:

- **Users:** Accounts used by individuals or processes to interact with the system.
- **Groups:** Collections of users sharing common access permissions.
- **Primary Group:** Default group assigned to a user, used for ownership of files and initial group permissions.
- **Secondary Groups:** Additional groups a user can belong to for accessing shared resources.
- **Switching Users:** `su` command for temporary user switching, `sudo` for executing commands as another user.



### 1. `/etc/passwd` File

The `/etc/passwd` file is a system file that stores essential information about user accounts. Historically, it contained user data such as usernames, user IDs (UIDs), group IDs (GIDs), home directories, and login shells. However, passwords used to be stored in this file, which posed security risks because anyone who could read `/etc/passwd` could potentially access password hashes.

#### Format of `/etc/passwd`:

The `/etc/passwd` file is formatted with seven colon-separated fields:

```bash
username:password:UID:GID:GECOS:home_directory:login_shell
```

- **`username`:** The name of the user account.
- **`password`:** Historically stored the hashed password, but now typically contains `x` or `*` to indicate that the password is stored in `/etc/shadow`.
- **`UID`:** User ID, a unique numeric identifier for the user.
- **`GID`:** Primary group ID, identifies the primary group for the user.
- **`GECOS`:** General information about the user (full name, phone number, etc.), often left empty.
- **`home_directory`:** The user's home directory where files and personal data are stored.
- **`login_shell`:** The default shell or command interpreter for the user when logging in.

#### Example `/etc/passwd` Entry:

```
john:x:1001:1001:John Doe:/home/john:/bin/bash
```

- `john`: Username
- `x`: Placeholder for the password (actual password stored in `/etc/shadow`)
- `1001`: UID
- `1001`: GID (John's primary group)
- `John Doe`: User's full name (GECOS field)
- `/home/john`: John's home directory
- `/bin/bash`: John's default login shell

### 2. `/etc/shadow` File

The `/etc/shadow` file is a critical file for storing password hashes and related account information securely. It is readable only by the root user (`sudo`), enhancing security by preventing unauthorized access to hashed passwords.

#### Format of `/etc/shadow`:

The `/etc/shadow` file is formatted with nine colon-separated fields:

```bash
username:password:last_change:min_age:max_age:warn_days:inactive_days:expire_date:reserved
```

- **`username`:** Username, must match a username in `/etc/passwd`.
- **`password`:** Encrypted password hash or a placeholder (`*` or `!` indicating no password or account is locked).
- **`last_change`:** The date of the last password change, represented as the number of days since January 1, 1970 (Unix epoch).
- **`min_age`:** Minimum number of days before a password change is allowed.
- **`max_age`:** Maximum number of days before the password must be changed.
- **`warn_days`:** Number of days before password expiration that the user is warned.
- **`inactive_days`:** Number of days after password expiration before the account is locked.
- **`expire_date`:** Date (in days since Unix epoch) when the account will be disabled.
- **`reserved`:** Reserved field for future use.

#### Example `/etc/shadow` Entry:

```bash
john:$6$8Z6Wc3VX$8vug0vll6G3Xs91BVb7wUpBNoWZGTVuvDc/UoZD1PrHjCnDp2V.WGZ0F6Xy84sbOnH4VpLoXO8bO.AQ8V64/:18884:0:99999:7:::
```

- `john`: Username (must match `/etc/passwd`)
- `$6$8Z6Wc3VX$8vug0vll6G3Xs91BVb7wUpBNoWZGTVuvDc/UoZD1PrHjCnDp2V.WGZ0F6Xy84sbOnH4VpLoXO8bO.AQ8V64/`: Encrypted password hash
- `18884`: Last password change (days since Unix epoch)
- `0`: Minimum age (0 days before password change allowed)
- `99999`: Maximum age (99999 days before password must be changed)
- `7`: Warn days (warn 7 days before password expires)
- `:::`: Other fields reserved

### Examples of `passwd` and `shadow` Usage:

#### Changing User Password with `passwd`:

To change a user's password, use the `passwd` command:

```bash
sudo passwd username
```

Replace `username` with the name of the user whose password you want to change. You will be prompted to enter and confirm the new password.

#### Editing `/etc/passwd` and `/etc/shadow`:

Directly editing these files is generally not recommended due to their critical role in system security. However, if necessary, you can use a text editor like `nano` or `vi` with root privileges (`sudo`) to modify them:

```bash
sudo nano /etc/passwd
sudo nano /etc/shadow
```

Make changes carefully, ensuring correct syntax and avoiding mistakes that could lock users out or compromise system security.

### Summary:

- **`/etc/passwd`:** Stores user account information except for passwords.
- **`/etc/shadow`:** Securely stores password hashes and related account information.
- **Usage:** Use `passwd` to change passwords and edit `/etc/passwd` or `/etc/shadow` with caution using root privileges when necessary.



### Command: `su - username`

The `su` command stands for "substitute user," and it allows you to switch to another user account on the system. When used with the `-` option and a `username`, it starts a new shell session with the environment of that user.

### Purpose:

The primary purpose of `su - username` is to:

- **Switch User:** Temporarily change the current user context to `username`, granting access to their environment, files, and permissions.
- **Execute Commands:** Execute commands as if logged in as `username`, inheriting their shell environment, PATH variables, and other settings.

### 

1. **`su` Command:**
   - **Usage:** `su [options] [username]`
   - **Options:** `-` (hyphen/dash) or `--login`: Simulates a full login session, setting up the environment as if the user had logged in directly.
2. **`username`:**
   - Specifies the user account to switch to. This can be any valid user on the system for which you have permissions to switch to.

### Detailed Explanation:

#### Authentication and Permissions:

- **Password Prompt:** When you enter `su - username`, the system prompts you for the password associated with `username`.
- **Root or `sudo` Required:** Typically, you need administrative privileges (root or `sudo` access) to switch to another user using `su`. This ensures security and prevents unauthorized access.

#### Environment Setup:

- **Shell Environment:** Upon successful authentication, `su - username` starts a new shell session (`/bin/bash` or as specified in `/etc/passwd`) with the environment variables and settings of `username`.
- **Home Directory:** Sets the current working directory to `username`'s home directory (`/home/username` by default).

#### Use Cases:

- **Administrative Tasks:** Often used by system administrators to perform administrative tasks that require elevated permissions or specific user environments.
- **Debugging and Testing:** Useful for testing user-specific configurations or troubleshooting issues related to user-specific environment variables and settings.
- **Security Considerations:** Provides a secure way to access another user's environment and files without compromising system integrity.

#### Ending the Session:

- **Exiting:** To return to the original user's session, simply type `exit` or press `Ctrl+D`. This terminates the shell session started with `su - username` and returns you to the original user's environment.

### Example Usage:

```
su - john
```

- Prompts for `john`'s password.
- If authenticated, starts a new shell session as user `john` with their environment variables and settings.

### Note:

- Always use `su -` with caution, especially when dealing with administrative tasks or accessing sensitive user data.
- Ensure you have proper permissions and authorization before switching users using `su - username`.



### Command: `usermod -aG group user`

#### Purpose:

To add an existing user to an additional group without removing the user from any existing groups.

### Syntax:

```bash
sudo usermod -aG group user
```

- **`sudo`**: Runs the command with superuser (root) privileges, which is necessary for modifying user accounts.
- **`usermod`**: The command used to modify user account information.
- **`-a`**: Stands for "append", which means to add the user to the specified group(s) without removing them from other groups they are already members of.
- **`-G`**: Specifies the supplementary group(s) to which the user should be added. Multiple groups can be specified, separated by commas.
- **`group`**: The name of the group to which you want to add the user.
- **`user`**: The username of the user account you want to modify.

### Example:

Add the user `john` to the `developers` group:

```bash
sudo usermod -aG developers john
```

### Explanation:

1. **`sudo`**: The `usermod` command requires superuser privileges to make changes to user accounts, so `sudo` is used to execute the command with the necessary permissions.
2. **`usermod`**: This command modifies user account settings.
3. **`-a` (append)**: Ensures the user is added to the new group without being removed from any other groups they are currently a member of. This option is crucial for preserving existing group memberships.
4. **`-G`**: Indicates that you are specifying supplementary groups.
5. **`developers`**: The name of the group you are adding the user to. This can be any valid group name on the system.
6. **`john`**: The username of the user account being modified.

### 

### Explanation of `%sudo ALL=(ALL:ALL) ALL` in `/etc/sudoers`

This line in the `/etc/sudoers` file allows members of the `sudo` group to execute any command as any user or group with root privileges.

#### Breakdown:

- **`%sudo`**: Specifies that this rule applies to all members of the `sudo` group. The `%` prefix indicates a user group.

- **`ALL`**: The hosts on which this rule is valid. `ALL` means this rule applies to all hosts.

- `ALL=(ALL:ALL)`

  :

  - The first `ALL` means the users can run commands as all users.
  - The second `ALL` (after the `=`) means they can run commands as all groups.

- **`ALL`**: The commands that the users can run. `ALL` means they can run all commands.

### 
