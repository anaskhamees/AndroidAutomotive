## Bash Script to organized files

```bash
#!/bin/bash

# Create source directory and populate it with example files
mkdir -p source_directory
cd source_directory
touch img.png img1.jpg img2.gif doc1.txt doc2.pdf doc3.doc doc4.ppt doc5.xml other1.zip other2.tar

# Create organized directories
mkdir -p ../organizedDirectory/images
mkdir -p ../organizedDirectory/documents
mkdir -p ../organizedDirectory/others

# Display the files to show the order of processing
echo "Files to be processed:"
ls -1

# Move files to organized directories based on their extensions
for file in *; do
    if [ -f "$file" ]; then
        case "$file" in
            *.png | *.jpg | *.gif)
                mv "$file" ../organizedDirectory/images
                echo "The Images moved Successfully: $file"
                ;;
            *.txt | *.pdf | *.doc | *.docx | *.ppt | *.pptx | *.xls | *.xlsx)
                mv "$file" ../organizedDirectory/documents
                echo "The Documents moved Successfully: $file"
                ;;
            *)
                mv "$file" ../organizedDirectory/others
                echo "The Others files moved Successfully: $file"
                ;;
        esac
    fi
done

```



### Explanation 

###  Create and Populate Source Directory

```bash
mkdir -p source_directory
cd source_directory
touch img.png img1.jpg img2.gif doc1.txt doc2.pdf doc3.doc doc4.ppt doc5.xml other1.zip other2.tar
```

- `mkdir -p source_directory`: Creates a directory named `source_directory`. The `-p` option ensures that no error is raised if the directory already exists.
- `cd source_directory`: Changes the current working directory to `source_directory`.
- `touch img.png img1.jpg img2.gif doc1.txt doc2.pdf doc3.doc doc4.ppt doc5.xml other1.zip other2.tar`: Creates a set of example files with various extensions within the `source_directory`.

### 2. Create Organized Directories

```bash
mkdir -p ../organizedDirectory/images
mkdir -p ../organizedDirectory/documents
mkdir -p ../organizedDirectory/others
```

- `mkdir -p ../organizedDirectory/images`: Creates the `images` directory inside `organizedDirectory` at the parent level. The `-p` option ensures no error if the directory already exists.
- `mkdir -p ../organizedDirectory/documents`: Creates the `documents` directory inside `organizedDirectory`.
- `mkdir -p ../organizedDirectory/others`: Creates the `others` directory inside `organizedDirectory`.

### 3. Display Files to Show Order of Processing

```bash
echo "Files to be processed:"
ls -1
```

- `echo "Files to be processed:"`: Prints a message to the terminal indicating that the following output will be the list of files to be processed.
- `ls -1`: Lists all files in the current directory (one file per line), showing the order in which they will be processed.

### 4. Move Files to Organized Directories Based on Their Extensions

```bash
for file in *; do
    if [ -f "$file" ]; then
        case "$file" in
            *.png | *.jpg | *.gif)
                mv "$file" ../organizedDirectory/images
                echo "The Images moved Successfully: $file"
                ;;
            *.txt | *.pdf | *.doc | *.docx | *.ppt | *.pptx | *.xls | *.xlsx)
                mv "$file" ../organizedDirectory/documents
                echo "The Documents moved Successfully: $file"
                ;;
            *)
                mv "$file" ../organizedDirectory/others
                echo "The Others files moved Successfully: $file"
                ;;
        esac
    fi
done
```

- `for file in *; do`: Begins a loop that iterates over every item in the current directory.

- `if [ -f "$file" ]; then`: Checks if the item is a regular file (not a directory or special file).

- ```
  case "$file" in
  ```

  : Begins a `case` statement to match the file against various patterns.

  - ```
    *.png | *.jpg | *.gif)
    ```

    : If the file matches any of these image extensions:

    - `mv "$file" ../organizedDirectory/images`: Moves the file to the `images` directory.
    - `echo "The Images moved Successfully: $file"`: Prints a success message for moving the image file.

  - ```
    *.txt | *.pdf | *.doc | *.docx | *.ppt | *.pptx | *.xls | *.xlsx)
    ```

    : If the file matches any of these document extensions:

    - `mv "$file" ../organizedDirectory/documents`: Moves the file to the `documents` directory.
    - `echo "The Documents moved Successfully: $file"`: Prints a success message for moving the document file.

  - `*)`: For any other files that do not match the above patterns:

    - `mv "$file" ../organizedDirectory/others`: Moves the file to the `others` directory.
    - `echo "The Others files moved Successfully: $file"`: Prints a success message for moving the other file.

- `esac`: Ends the `case` statement.

- `fi`: Ends the `if` statement.

- `done`: Ends the `for` loop.

### 
