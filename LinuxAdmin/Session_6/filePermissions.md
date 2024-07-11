# Admin Linux



**############# I will organize them soooon ###############**







## 1. File Types

### Regular File (`-`)

- **Example**: A text file, a script, or an executable.
- **Command to create**: `touch filename.txt` or `echo "Hello" > file.txt`
- **List command**: `ls -l`

### Directory (`d`)

- **Example**: A folder containing other files or directories.
- **Command to create**: `mkdir dirname`
- **List command**: `ls -ld dirname`

### Character Device File (`c`)

- **Example**: A terminal or serial port (e.g., `/dev/tty1`).
- **Command to create**: `sudo mknod /dev/mychardev c 89 1` (requires root)
- **List command**: `ls -l /dev/mychardev`

### Block Device File (`b`)

- **Example**: A hard disk partition (e.g., `/dev/sda1`).
- **Command to create**: `sudo mknod /dev/myblockdev b 89 1` (requires root)
- **List command**: `ls -l /dev/myblockdev`

### Named Pipe (FIFO) (`p`)

- **Example**: A pipe used for inter-process communication.
- **Command to create**: `mkfifo mypipe`
- **List command**: `ls -l mypipe`

### Socket (`s`)

- **Example**: A Unix domain socket (e.g., `/var/run/docker.sock`).
- **Command to create**: `sudo socat UNIX-LISTEN:/tmp/mysocket,fork EXEC:/bin/cat &` (requires `socat` package)
- **List command**: `ls -l /tmp/mysocket`

### Symbolic Link (`l`)

- **Example**: A shortcut or alias to another file or directory.
- **Command to create**: `ln -s /path/to/target symlinkname`
- **List command**: `ls -l symlinkname`

### Example Commands and Outputs

1. **Regular File**

   ```bash
   touch regularfile.txt
   echo "This is a regular file" > regularfile.txt
   ls -l regularfile.txt
   ```

   Output:

   ```bash
   -rw-r--r-- 1 user user 22 Jul 10 12:00 regularfile.txt
   ```

2. **Directory**

   ```bash
   mkdir mydirectory
   ls -ld mydirectory
   ```

   Output:

   ```bash
   drwxr-xr-x 2 user user 4096 Jul 10 12:00 mydirectory
   ```

3. **Character Device File**

   ```bash
   sudo mknod /dev/mychardev c 89 1
   ls -l /dev/mychardev
   ```

   Output:

   ```bash
   crw-r--r-- 1 root root 89, 1 Jul 10 12:00 /dev/mychardev
   ```

4. **Block Device File**

   ```bash
   sudo mknod /dev/myblockdev b 89 1
   ls -l /dev/myblockdev
   ```

   Output:

   ```bash
   brw-r--r-- 1 root root 89, 1 Jul 10 12:00 /dev/myblockdev
   ```

5. **Named Pipe (FIFO)**

   ```bash
   mkfifo mypipe
   ls -l mypipe
   ```

   Output:

   ```bash
   prw-r--r-- 1 user user 0 Jul 10 12:00 mypipe
   ```

6. **Socket**

   ```bash
   sudo socat UNIX-LISTEN:/tmp/mysocket,fork EXEC:/bin/cat &
   ls -l /tmp/mysocket
   ```

   Output:

   ```bash
   srwxr-xr-x 1 user user 0 Jul 10 12:00 /tmp/mysocket
   ```

7. **Symbolic Link**

   ```bash
   ln -s /path/to/target symlinkname
   ls -l symlinkname
   ```

   Output:

   ```bash
   lrwxrwxrwx 1 user user 14 Jul 10 12:00 symlinkname -> /path/to/target
   ```

## 2. User Groups

When creating a user in Linux, the user is assigned to a primary group and can also be added to one or more secondary groups. Here's an explanation and examples of how to manage users and groups.

### Primary Group

- **Primary Group**: Each user has a primary group that is created when the user is created. The primary group is typically the same as the username.
- **Command to create a user with a primary group**: `useradd username`

### Secondary Groups

- **Secondary Groups**: Users can also be added to additional groups. These are known as secondary groups. Secondary groups are useful for providing additional permissions to the user.
- **Command to add a user to secondary groups**: `usermod -aG group1,group2 username`

### Example Commands and Explanations

1. **Create a User**

   ```bash
   sudo useradd john
   ```

   This command creates a user named `john`. By default, it also creates a group named `john` and assigns it as John's primary group.

2. **Set Password for the User**

   ```bash
   sudo passwd john
   ```

   This command sets a password for the user `john`.

3. **Create Secondary Groups**

   ```bash
   sudo groupadd developers
   sudo groupadd testers
   ```

   These commands create two groups named `developers` and `testers`.

4. **Add User to Secondary Groups**

   ```bash
   sudo usermod -aG developers,testers john
   ```

   This command adds the user `john` to the `developers` and `testers` groups as secondary groups.

5. **Check User and Group Information**

   ```bash
   id john
   ```

   Output:

   ```bash
   uid=1001(john) gid=1001(john) groups=1001(john),1002(developers),1003(testers)
   ```

### Useful Commands

1. **Creating a User with a Specific Primary Group**

   ```bash
   sudo useradd -g groupname username
   ```

2. **Adding User to Secondary Groups**

   ```bash
   sudo usermod -aG group1,group2 username
   ```

3. **Creating a Group**

   ```bash
   sudo groupadd groupname
   ```

4. **Checking User Information**

   ```bash
   id username
   ```

5. **Listing All Groups**

   ```bash
   cat /etc/group
   ```

6. **Listing All Users**

   ```bash
   cat /etc/passwd
   ```

### Detailed Example

1. **Create User with Specific Primary Group**

   ```bash
   sudo groupadd admin
   sudo useradd -g admin alice
   sudo passwd alice
   ```

   This creates a user `alice` with `admin` as her primary group and sets her password.

2. **Add User to Secondary Groups**

   ```bash
   sudo groupadd devops
   sudo groupadd qa
   sudo usermod -aG devops,qa alice
   ```

   This adds `alice` to `devops` and `qa` as secondary groups.

3. **Verify User's Groups**

   ```bash
   id alice
   ```

   Output:

   ```bash
   uid=1002(alice) gid=1002(admin) groups=1002(admin),1003(devops),1004(qa)
   ```



## 3. Permissions

 In Linux, file permissions control the actions that users can perform on files and directories. There are three types of basic permissions and several special permissions. Here's a detailed overview:

### Basic Permissions

Each file and directory has three types of permissions for three different categories of users:

1. **Owner (u)**: The user who owns the file.
2. **Group (g)**: The group that owns the file.
3. **Others (o)**: All other users.

The three types of permissions are:

1. **Read (r)**: Permission to read the file or list the directory's contents.
2. **Write (w)**: Permission to modify the file or directory's contents.
3. **Execute (x)**: Permission to execute the file (if it's a script or program) or access the directory.

### Viewing Permissions

Use the `ls -l` command to view the permissions of files and directories:

```bash
ls -l
```

Output example:

```bash
-rwxr-xr-- 1 user group  4096 Jul 10 12:00 filename
```

- `-rwxr-xr--`: The first character indicates the file type (`-` for regular files, `d` for directories, etc.).
- `rwxr-xr--`: The next nine characters represent the permissions for the owner, group, and others.

### Changing Permissions

Use the `chmod` command to change file permissions:

```bash
chmod [permissions] [filename]
```

Permissions can be set using symbolic or numeric modes.

#### Symbolic Mode

- `u` (user/owner), `g` (group), `o` (others), `a` (all)
- `+` (add), `-` (remove), `=` (set)

Example:

```bash
chmod u+x filename      # Add execute permission for the owner
chmod g-w filename      # Remove write permission for the group
chmod o=r filename      # Set read-only permission for others
chmod a=rwx filename    # Set read, write, and execute permissions for all
```

#### Numeric Mode

- Read (r) = 4
- Write (w) = 2
- Execute (x) = 1

Example:

```bash
chmod 755 filename      # rwxr-xr-x
chmod 644 filename      # rw-r--r--
chmod 777 filename      # rwxrwxrwx
```

### Special Permissions

1. **Setuid (Set User ID)**: Allows a user to run an executable with the file owner's privileges.
2. **Setgid (Set Group ID)**: Allows a user to run an executable with the file group's privileges or ensures that files created within a directory inherit the directory's group.
3. **Sticky Bit**: Prevents users from deleting or renaming files in a directory unless they own the file or directory.

#### Setuid

- Symbol: `s` in the owner's execute field.
- Numeric: `4` as the leading digit.

**Examples to change the permissions:**



```bash
chmod u+s filename      # Symbolic
chmod 4755 filename     # Numeric
```

#### Setgid

- Symbol: `s` in the group's execute field.
- Numeric: `2` as the leading digit.

Example:

```bash
chmod g+s filename      # Symbolic
chmod 2755 filename     # Numeric
```

#### Sticky Bit

- Symbol: `t` in the others' execute field.
- Numeric: `1` as the leading digit.

Example:

```bash
chmod o+t directory     # Symbolic
chmod 1777 directory    # Numeric
```

### Examples and Commands

**View Permissions**

```bash
ls -l filename
```

### 1. Change Permissions (Symbolic Mode)

The `chmod` command in symbolic mode allows you to modify permissions using a symbolic representation.

#### Example 1: Add execute permission for the owner

```bash
chmod u+x filename
```

- `u`: Refers to the user (owner).
- `+`: Means add the permission.
- `x`: Represents execute permission.

**Use case**: This is often used to make a script or program executable by its owner.

#### Example 2: Remove write permission for the group

```bash
chmod g-w filename
```

- `g`: Refers to the group.
- `-`: Means remove the permission.
- `w`: Represents write permission.

**Use case**: This can be used to prevent a group from modifying a file.

#### Example 3: Set read-only permission for others

```bash
chmod o=r filename
```

- `o`: Refers to others.
- `=`: Sets the permission exactly to the specified value.
- `r`: Represents read permission.

**Use case**: This ensures that all other users can only read the file, not write or execute it.

#### Example 4: Set read, write, and execute permissions for all

```bash
chmod a=rwx filename
```

- `a`: Refers to all users (user, group, and others).
- `=`: Sets the permission exactly to the specified value.
- `rwx`: Represents read, write, and execute permissions.

**Use case**: This gives full permissions to everyone, which is rarely advisable for security reasons but might be useful for temporary or specific purposes.

### 2. Change Permissions (Numeric Mode)

The `chmod` command in numeric mode allows you to set permissions using a three-digit octal number.

#### Example 1: Set permissions to rwxr-xr-x

```bash
chmod 755 filename
```

- `7` (rwx): Owner has read, write, and execute permissions.
- `5` (r-x): Group has read and execute permissions.
- `5` (r-x): Others have read and execute permissions.

**Use case**: Common for scripts and programs where the owner needs full control, and others can execute but not modify.

#### Example 2: Set permissions to rw-r--r--

```bash
chmod 644 filename
```

- `6` (rw-): Owner has read and write permissions.
- `4` (r--): Group has read-only permissions.
- `4` (r--): Others have read-only permissions.

**Use case**: Common for configuration files where the owner can modify, and others can only read.

#### Example 3: Set permissions to rwxrwxrwx

```bash
chmod 777 filename
```

- `7` (rwx): Owner has read, write, and execute permissions.
- `7` (rwx): Group has read, write, and execute permissions.
- `7` (rwx): Others have read, write, and execute permissions.

**Use case**: Allows everyone full access to the file, generally not recommended for security reasons.

### 3. Setuid (Set User ID)

The `setuid` permission allows a user to execute a file with the permissions of the file owner.

#### Example: Setuid with symbolic mode

```bash
chmod u+s filename
```

- `u+s`: Adds the setuid permission to the owner.

#### Example: Setuid with numeric mode

```bash
chmod 4755 filename
```

- `4`: Setuid bit.
- `755`: File permissions (rwxr-xr-x).

**Use case**: Commonly used for executables like `passwd`, where normal users need to execute with root privileges.

### 4. Setgid (Set Group ID)

The `setgid` permission allows a file to be executed with the permissions of the file's group or ensures that files created in a directory inherit the directory's group.

#### Example: Setgid with symbolic mode

```bash
chmod g+s filename
```

- `g+s`: Adds the setgid permission to the group.

#### Example: Setgid with numeric mode

```bash
chmod 2755 filename
```

- `2`: Setgid bit.
- `755`: File permissions (rwxr-xr-x).

**Use case**: Often used for directories to ensure all files created within inherit the directory's group, maintaining consistent group ownership.

### 5. Sticky Bit

The sticky bit prevents users from deleting or renaming files in a directory unless they own the file or directory.

#### Example: Sticky bit with symbolic mode

```bash
chmod o+t directory
```

- `o+t`: Adds the sticky bit to others.

#### Example: Sticky bit with numeric mode

```bash
chmod 1777 directory
```

- `1`: Sticky bit.
- `777`: Directory permissions (rwxrwxrwx).

**Use case**: Commonly used for shared directories like `/tmp` to prevent users from deleting each other's files.

### Summary

- **Symbolic Mode**: Allows you to modify specific permissions for the user (u), group (g), others (o), or all (a) using `+`, `-`, and `=`.
- **Numeric Mode**: Uses octal numbers to set permissions in a more compact form.
- **Special Permissions**: Setuid, setgid, and the sticky bit provide additional control over how files and directories are accessed and executed.



### Example Usage

1. **Create a Script and Change Permissions**

   ```bash
   echo -e "#!/bin/bash\n echo Hello, World!" > hello.sh
   chmod +x hello.sh
   ./hello.sh
   ```

2. **Create a Directory with Setgid and Sticky Bit**

   ```bash
   mkdir shared
   chmod 2775 shared      # Setgid
   chmod 1777 shared      # Sticky bit
   ls -ld shared
   ```



### Read Permissions

Commands that need read permissions typically require access to the contents of a file or directory. Examples include:

1. **cat**: Displays the contents of a file.

   ```bash
   cat filename
   ```

2. **less/more**: Views the contents of a file one screen at a time.

   ```bash
   less filename
   more filename
   ```

3. **head/tail**: Views the beginning or end of a file.

   ```bash
   head filename
   tail filename
   ```

4. **grep**: Searches for patterns in a file.

   ```bash
   grep "pattern" filename
   ```

5. **find**: Searches for files and directories.

   ```bash
   find /path -name filename
   ```

6. **ls**: Lists directory contents.

   ```bash
   ls
   ```

7. **stat**: Displays detailed information about a file or directory.

   ```bash
   stat filename
   ```

### Write Permissions

Commands that need write permissions typically modify the contents of a file or directory. Examples include:

1. **touch**: Creates an empty file or updates the timestamp of a file.

   ```bash
   touch filename
   ```

2. **echo**: Writes a string to a file (often used with redirection).

   ```bash
   echo "text" > filename
   ```

3. **nano/vim**: Edits a file using a text editor.

   ```bash
   nano filename
   vim filename
   ```

4. **rm**: Deletes a file.

   ```bash
   rm filename
   ```

5. **mv**: Moves or renames a file or directory.

   ```bash
   mv oldname newname
   ```

6. **cp**: Copies a file or directory.

   ```bash
   cp source destination
   ```

7. **mkdir**: Creates a new directory.

   ```bash
   mkdir dirname
   ```

8. **rmdir**: Deletes an empty directory.

   ```bash
   rmdir dirname
   ```

9. **chmod**: Changes file permissions.

   ```bash
   chmod 755 filename
   ```

10. **chown**: Changes file owner and group.

    ```bash
    chown user:group filename
    ```

11. **truncate**: Shrinks or extends the size of a file to the specified size.

    ```bash
    truncate -s 0 filename
    ```

### Execute Permissions

Commands that need execute permissions typically require the ability to run a file as a program or script, or to access a directory. Examples include:

1. **./script.sh**: Executes a script.

   ```bash
   ./script.sh
   ```

2. **bash**: Executes a script with the Bash shell.

   ```bash
   bash script.sh
   ```

3. **sh**: Executes a script with the SH shell.

   ```bash
   sh script.sh
   ```

4. **python**: Runs a Python script.

   ```bash
   python script.py
   ```

5. **java**: Runs a Java program.

   ```bash
   java Program
   ```

6. **gcc**: Compiles and runs C programs (needs execute permission for the resulting binary).

   ```bash
   gcc program.c -o program
   ./program
   ```

7. **find**: If using the `-exec` option, it requires execute permissions on the target files.

   ```bash
   find /path -name "*.sh" -exec chmod +x {} \;
   ```

8. **mkdir**: Needs execute permission on the parent directory to create a new directory within it.

   ```bash
   mkdir newdir
   ```

9. **cd**: Changes the current directory, requiring execute permission on the target directory.

   ```bash
   cd targetdir
   ```

### Summary

- **Read Permissions**: Required for commands that read file contents or list directory contents (e.g., `cat`, `ls`, `grep`).
- **Write Permissions**: Required for commands that modify file contents or directory structure (e.g., `touch`, `rm`, `mv`).
- **Execute Permissions**: Required for commands that execute scripts, programs, or change directories (e.g., `./script.sh`, `cd`).



### 3. Special Permissions

In Linux, special permissions provide additional control over how files and directories are accessed and executed. There are three special permissions:

1. **Setuid (Set User ID)**
2. **Setgid (Set Group ID)**
3. **Sticky Bit**

### 1. Setuid (Set User ID)

When the setuid bit is set on an executable file, it allows users to execute the file with the permissions of the file's owner. This is typically used for programs that require elevated privileges.

#### Example and Commands

**Step-by-Step Explanation:**

1. **Create a Test Script:**

   ```bash
   echo -e "#!/bin/bash\n echo 'Current user: $(whoami)'" > test_script.sh
   ```

2. **Make the Script Executable:**

   ```bash
   chmod +x test_script.sh
   ```

3. **Change Owner to Root (for demonstration purposes):**

   ```bash
   sudo chown root test_script.sh
   ```

4. **Set the Setuid Bit:**

   - **Symbolic Mode:**

     ```bash
     sudo chmod u+s test_script.sh
     ```

   - **Numeric Mode:**

     ```bash
     sudo chmod 4755 test_script.sh
     ```

5. **Verify Permissions:**

   ```bash
   ls -l test_script.sh
   ```

   Output:

   ```bash
   -rwsr-xr-x 1 root root 32 Jul 10 12:00 test_script.sh
   ```

6. **Run the Script:**

   ```bash
   ./test_script.sh
   ```

   Expected Output:

   ```bash
   Current user: root
   ```

### 2. Setgid (Set Group ID)

When the setgid bit is set on a directory, files created within the directory inherit the group ownership of the directory rather than the primary group of the user who created the file. For executable files, it allows users to run the file with the permissions of the file's group.

#### Example and Commands

**Step-by-Step Explanation:**

1. **Create a Directory:**

   ```bash
   mkdir test_dir
   ```

2. **Change Group Ownership (for demonstration purposes):**

   ```bash
   sudo chown :groupname test_dir
   ```

   - **`sudo`**

     - **`sudo`** stands for "superuser do."

     - It allows a permitted user to execute a command as the superuser (root) or another user, as specified by the security policy.

     - In this case, it is used to execute the `chown` command with elevated privileges, which is often necessary for changing ownership of system files or files not owned by the current user.

   - **`chown`**

     - **`chown`** stands for "change owner."
     - It is a command used to change the ownership of files and directories.
     - The basic syntax of `chown` is `chown [OPTIONS] USER:GROUP FILE`, where `USER` is the new owner and `GROUP` is the new group.

     -  **`:groupname`**

     - In the `chown` command, specifying `:groupname` without a username changes only the group ownership.

     - **`groupname`** is the name of the group to which you want to change the group ownership of `test_dir`.

     - The colon (`:`) before `groupname` indicates that only the group ownership is being modified, not the user ownership.

   -  **`test_dir`**
     - **`test_dir`** is the name of the directory (or file) whose group ownership you want to change.

   

   3. **Set the Setgid Bit**

      The Setgid (Set Group ID) bit is a special permission that can be set on directories. When the Setgid bit is set on a directory, new files and directories created within it inherit the group ownership of the directory rather than the creating user's default group.

      **Symbolic Mode**

   ```bash
   sudo chmod g+s test_dir
   ```

   - **`sudo`**: Executes the command with superuser privileges.
   - **`chmod`**: Changes the file mode bits (permissions) of a file or directory.
   - **`g+s`**: Adds the Setgid bit. The `g` specifies the group, and `+s` adds the Setgid bit.
   - **`test_dir`**: The directory on which the Setgid bit is being set.

   #### Numeric Mode

   ```bash
   sudo chmod 2755 test_dir
   ```

   - **`sudo`**: Executes the command with superuser privileges.
   - **`chmod`**: Changes the file mode bits (permissions) of a file or directory.
   - `2755`: The numeric representation of the permissions you are setting.
     - The first digit (`2`) sets the Setgid bit.
     - The following three digits (755) set the standard read, write, and execute permissions.
       - `7` corresponds to read (`r`), write (`w`), and execute (`x`) permissions for the owner.
       - `5` corresponds to read (`r`) and execute (`x`) permissions for the group.
       - `5` corresponds to read (`r`) and execute (`x`) permissions for others.

   So, `2755` means the directory has read, write, and execute permissions for the owner, and read and execute permissions for the group and others, with the Setgid bit set.

   **3. Verify Permissions**

   To verify that the permissions have been set correctly, you can use the `ls -ld` command:

   ```bash
   ls -ld test_dir
   ```

   This command lists the directory's detailed information, including its permissions, ownership, and other attributes.

   ### Expected Output:

   ```
   bash
   Copy code
   drwxr-sr-x 2 user groupname 4096 Jul 10 12:00 test_dir
   ```

   #### Breaking Down the Output:

   - **`drwxr-sr-x`**:
     - `d`: Indicates it is a directory.
     - `rwx`: Read, write, and execute permissions for the owner.
     - `r-s`: Read and execute permissions for the group, with the Setgid bit set (`s` replaces the `x`).
     - `r-x`: Read and execute permissions for others.
   - **`2`**: Number of hard links to the directory.
   - **`user`**: Owner of the directory.
   - **`groupname`**: Group owner of the directory.
   - **`4096`**: Size of the directory in bytes.
   - **`Jul 10 12:00`**: Last modification date and time.
   - **`test_dir`**: Name of the directory.

   ### Summary

   1. **Setting the Setgid Bit**:

      - **Symbolic Mode**: `sudo chmod g+s test_dir`
      - **Numeric Mode**: `sudo chmod 2755 test_dir`

   2. **Verifying Permissions**:

      - Use `ls -ld test_dir` to check the permissions.

      The output should indicate that the Setgid bit is set, with the group execute permission replaced by `s`.

3. **Create a File in the Directory:**

   ```bash
   touch test_dir/newfile
   ```

4. **Verify Group Ownership of the New File:**

   ```bash
   ls -l test_dir/newfile
   ```

   Output:

   ```bash
   -rw-r--r-- 1 user groupname 0 Jul 10 12:00 newfile
   ```

### 3. Sticky Bit

When the sticky bit is set on a directory, it ensures that only the owner of a file can delete or rename the file within that directory. This is commonly used for shared directories like `/tmp`.

#### Example and Commands

**Step-by-Step Explanation:**

1. **Create a Directory:**

   ```bash
   mkdir shared_dir
   ```

2. **Set the Sticky Bit:**

   - **Symbolic Mode:**

     ```bash
     chmod +t shared_dir
     ```

     **`chmod`**: This command is used to change the file mode bits (permissions) of files and directories.

     **`+t`**: This adds the sticky bit to the directory.

     **`shared_dir`**: This is the directory on which the sticky bit is being set.

   - **Numeric Mode:**

     ```bash
     chmod 1777 shared_dir
     ```
     
     - **`chmod`**: As before, this command changes the file mode bits of files and directories.
     
     - `1777`
     
       : This is the numeric representation of the permissions you are setting:
     
       - The first digit (`1`) sets the sticky bit.
       - The following three digits (777) set the standard read, write, and execute permissions for the owner, group, and others.
         - `7` corresponds to read (`r`), write (`w`), and execute (`x`) permissions all being set (`rwx`).
     
     So, `1777` means the directory has read, write, and execute permissions for the owner, group, and others, plus the sticky bit.

3. **Verify Permissions:**

   ```bash
   ls -ld shared_dir
   ```

   **`ls`**: This command lists information about files and directories.

   **`-l`**: This option tells `ls` to use a long listing format, which displays detailed information including permissions, number of links, owner, group, size, and timestamp.

   **`-d`**: This option tells `ls` to list directory entries instead of their contents. Without `-d`, `ls` would list the contents of `shared_dir` rather than the directory itself.

   **`shared_dir`**: This is the directory whose permissions you want to verify.

   

   Output:

   ```bash
   drwxrwxrwt 2 user group 4096 Jul 10 12:00 shared_dir
   ```

   1. **`d`**

   The first character indicates the type of file:

   - `d`: Directory
   - `-`: Regular file
   - `l`: Symbolic link
   - Other characters (e.g., `b`, `c`, `s`, `p`) can indicate different types of special files.

   In this case, `d` means it is a directory.

   **2. `rwxrwxrwt`**

   The next nine characters represent the permissions for the owner, group, and others. They are divided into three groups of three characters each:

   - **Owner permissions (`rwx`)**:
     - `r`: Read permission
     - `w`: Write permission
     - `x`: Execute permission
   - **Group permissions (`rwx`)**:
     - `r`: Read permission
     - `w`: Write permission
     - `x`: Execute permission
   - **Others permissions (`rwt`)**:
     - `r`: Read permission
     - `w`: Write permission
     - `t`: Sticky bit set (indicates that only the owner of the file/directory or the root user can delete or rename files within this directory)

   The `t` in the others' permission section replaces what would normally be the execute (`x`) permission. When the sticky bit is set, it ensures that only the file owner can delete or rename the files within the directory.

    **3. `2`**

   The number immediately following the permissions (`2`) indicates the number of hard links to the directory. Directories typically have at least two hard links: one for the directory itself and one for its `.` (current directory) entry.

   **4. `user`**

   The next field (`user`) indicates the owner of the directory. This is the username of the person who owns the directory.

   **5. `group`**

   The following field (`group`) indicates the group associated with the directory. This is the group name.

   **6. `4096`**

   This number (`4096`) represents the size of the directory in bytes. For directories, this value is typically a standard size, often 4096 bytes, depending on the filesystem.

   **7. `Jul 10 12:00`**

   This timestamp indicates the last modification time of the directory. It shows the month (`Jul`), the day of the month (`10`), and the time (`12:00`).

   **8. `shared_dir`**

   The final part is the name of the directory (`shared_dir`).

   ### Summary

   Putting it all together, this output indicates that `shared_dir` is a directory with read, write, and execute permissions for the owner and group, and read, write, and execute permissions for others, with the sticky bit set. The directory has 2 hard links, is owned by the user `user` and the group `group`, has a size of 4096 bytes, and was last modified on July 10 at 12:00.

   

   ### **Create Files by Different Users (simulated)**

   #### Step 1.1: Create a File as the Current User

   ```bash
   touch shared_dir/file1
   ```

   - **`touch shared_dir/file1`**: The `touch` command is used to create an empty file named `file1` inside the `shared_dir` directory. This file is created by the current user, who owns this session in the terminal.

   #### Step 1.2: Create a File as Another User

   ```bash
   sudo -u anotheruser touch shared_dir/file2
   ```

   - **`sudo`**: This command allows you to run programs with the security privileges of another user (by default, as the superuser).
   - **`-u anotheruser`**: This option tells `sudo` to run the command as `anotheruser` instead of the default superuser.
   - **`touch shared_dir/file2`**: This command, run as `anotheruser`, creates an empty file named `file2` inside the `shared_dir` directory.

   In this setup:

   - `file1` is owned by the current user.
   - `file2` is owned by `anotheruser`.

   ### 2. **Verify that Only File Owners Can Delete Their Files**

   #### Step 2.1: Attempt to Delete `file2` as the Current User

   ```bash
   rm shared_dir/file2  # Permission denied if not owner
   ```

   - **`rm shared_dir/file2`**: The `rm` command is used to remove (delete) the file named `file2` inside the `shared_dir` directory.
   - If you try to delete `file2` as the current user (who did not create `file2`), you should get a "Permission denied" error. This is because the sticky bit on `shared_dir` prevents users from deleting files they do not own.

   #### Step 2.2: Delete `file2` as `anotheruser`

   ```bash
   sudo -u anotheruser rm shared_dir/file2  # Successful if owner
   ```

   - **`sudo -u anotheruser rm shared_dir/file2`**: This command uses `sudo` to run the `rm` command as `anotheruser`, who is the owner of `file2`.
   - Since `anotheruser` owns `file2`, this command will successfully delete `file2`.

   ### Recap

   1. **Creating Files**:
      - You created `file1` as the current user.
      - You created `file2` as `anotheruser`.
   2. **Verifying Deletion Permissions**:
      - Attempting to delete `file2` as the current user results in a **"Permission denied"** error because the sticky bit prevents non-owners from deleting files in the directory.
      - Deleting `file2` as `anotheruser` is successful because `anotheruser` is the owner of the file.

   --------------------------------------------------------------

   #### Summary

   - **Setuid:**
     - Symbolic: `chmod u+s filename`
     - Numeric: `chmod 4755 filename`
   - **Setgid:**
     - Symbolic: `chmod g+s filename` or `chmod g+s directory`
     - Numeric: `chmod 2755 filename` or `chmod 2755 directory`
   - **Sticky Bit:**
     - Symbolic: `chmod +t directory`
     - Numeric: `chmod 1777 directory`

---------------------------------------------------

### Tricks in Permissions

### 1. groupmems Command

The `groupmems` command is used to administer memberships in a group. It allows the administrator to manage which users are members of a specified group. This command is part of the `shadow` package and is useful for modifying group memberships directly from the command line.

### `groupmems` Command Syntax

```bash
groupmems -g groupname [options]
```

- **`-g groupname`**: Specifies the group for which you want to manage memberships.
- **`[options]`**: Various options to add, delete, or list members of the group.

### Common Options

- **`-a username`**: Add a user to the specified group.
- **`-d username`**: Delete a user from the specified group.
- **`-l`**: List the members of the specified group.
- **`-h`**: Display help information for the `groupmems` command.

### Examples

#### 1. Add a User to a Group

```bash
sudo groupmems -g groupname -a username
```

- **`sudo`**: Runs the command with superuser privileges.
- **`groupmems -g groupname -a username`**: Adds the specified `username` to the `groupname` group.

#### 2. Delete a User from a Group

```bash
sudo groupmems -g groupname -d username
```

- **`sudo`**: Runs the command with superuser privileges.
- **`groupmems -g groupname -d username`**: Deletes the specified `username` from the `groupname` group.

#### 3. List Members of a Group

```bash
groupmems -g groupname -l
```

- **`groupmems -g groupname -l`**: Lists all the members of the specified `groupname` group.

### Example Scenario

Let's assume you have a group named `developers` and you want to manage its members.

#### Adding a User to the Group

```bash
sudo groupmems -g developers -a john
```

This command adds the user `john` to the `developers` group.

#### Deleting a User from the Group

```bash
sudo groupmems -g developers -d john
```

This command removes the user `john` from the `developers` group.

#### Listing Members of the Group

```bash
groupmems -g developers -l
```

This command lists all users who are members of the `developers` group.

### Summary

- **`groupmems`** is a command for managing group memberships.
- **`-g groupname`** specifies the group.
- Options:
  - `-a username`: Adds a user to the group.
  - `-d username`: Deletes a user from the group.
  - `-l`: Lists the members of the group.
  - `-h`: Displays help information.
