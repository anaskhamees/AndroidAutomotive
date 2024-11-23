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

