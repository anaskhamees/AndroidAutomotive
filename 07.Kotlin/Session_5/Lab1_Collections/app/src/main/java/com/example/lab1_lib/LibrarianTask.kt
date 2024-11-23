package com.example.lab1_lib

open class Person(var name: String, var ID: String)

class Librarian(name: String, ID: String, var password: String) : Person(name, ID)

class User(name: String, ID: String, var job: String) : Person(name, ID)

open class LibItem(var title: String, var ISBN: String, var publication: String, var pagesNumber: Int) {
    var availableFlag: Boolean = true

    fun isAvailable(): Boolean {
        return availableFlag
    }

    fun getItem() {
        availableFlag = false
    }

    fun backItem() {
        availableFlag = true
    }
}

class Book(title: String, ISBN: String, publication: String, pagesNumber: Int) :
    LibItem(title, ISBN, publication, pagesNumber)

class Magazine(title: String, ISBN: String, publication: String, pagesNumber: Int) :
    LibItem(title, ISBN, publication, pagesNumber)

class Journal(title: String, ISBN: String, publication: String, pagesNumber: Int) :
    LibItem(title, ISBN, publication, pagesNumber)

class LibraryDatabase {

    private var availableBooks: MutableList<LibItem> = mutableListOf()
    private var borrowedBooks: MutableMap<LibItem, User> = mutableMapOf()

    fun addBook(book: LibItem) {
        availableBooks.add(book)
        println("Added: ${book.title}")
    }

    fun lendBook(book: LibItem, user: User) {
        if (book.isAvailable()) {
            book.getItem()
            borrowedBooks[book] = user
            println("${user.name} borrowed: ${book.title}")
        } else {
            println("Denied, ${book.title} is not available.")
        }
    }

    fun viewAvailableBooks() {
        println("Available Books:")
        availableBooks.filter { it.isAvailable() }
            .forEach { println("- ${it.title} (ISBN: ${it.ISBN})") }
    }

    fun searchForBook(title: String) {
        val foundBooks = availableBooks.filter { it.title.contains(title, ignoreCase = true) }
        if (foundBooks.isNotEmpty()) {
            println("Found Books in The Library:")
            foundBooks.forEach { println("- ${it.title} (ISBN: ${it.ISBN})") }
        } else {
            println("No books found with the title: $title")
        }
    }

    fun receiveBookFromBorrower(book: LibItem, user: User) {
        if (borrowedBooks.containsKey(book) && borrowedBooks[book] == user) {
            book.backItem()
            borrowedBooks.remove(book)
            println("${user.name} returned: ${book.title}")
        } else {
            println("${user.name} did not borrow: ${book.title}")
        }
    }

    fun getAvailableBooks(): List<LibItem> {
        return availableBooks.filter { it.availableFlag }
    }
}

fun main() {

    println("Enter Librarian Name: ")
    val librarianName = readLine() ?: ""
    println("Enter Librarian ID: ")
    val librarianID = readLine() ?: ""
    println("Enter Librarian Password: ")
    val librarianPassword = readLine() ?: ""

    val librarian = Librarian(librarianName, librarianID, librarianPassword)

    println("Enter User Name: ")
    val userName = readLine() ?: ""
    println("Enter User ID: ")
    val userID = readLine() ?: ""
    println("Enter User Job: ")
    val userJob = readLine() ?: ""

    val user = User(userName, userID, userJob)

    val libraryDatabase = LibraryDatabase()

    // Adding books to the library
    println("Enter Book Title: ")
    val bookTitle = readLine() ?: ""
    println("Enter Book ISBN: ")
    val bookISBN = readLine() ?: ""
    println("Enter Book Publication: ")
    val bookPublication = readLine() ?: ""
    println("Enter Number of Pages: ")
    val bookPages = readLine()?.toIntOrNull() ?: 0

    val book = Book(bookTitle, bookISBN, bookPublication, bookPages)
    libraryDatabase.addBook(book)

    // Add Journal to the library
    println("Enter Journal title:")
    val journalTitle = readLine() ?: ""
    println("Enter Journal ISBN:")
    val journalISBN = readLine() ?: ""
    println("Enter Journal publication name:")
    val journalPublication = readLine() ?: ""
    println("Enter Journal pages number:")
    val journalPages = readLine()?.toIntOrNull() ?: 0

    val journal = Journal(journalTitle, journalISBN, journalPublication, journalPages)
    libraryDatabase.addBook(journal)

    // Add Magazine to the library
    println("Enter Magazine title:")
    val magazineTitle = readLine() ?: ""
    println("Enter Magazine ISBN:")
    val magazineISBN = readLine() ?: ""
    println("Enter Magazine publication name:")
    val magazinePublication = readLine() ?: ""
    println("Enter Magazine pages number:")
    val magazinePages = readLine()?.toIntOrNull() ?: 0

    val magazine = Magazine(magazineTitle, magazineISBN, magazinePublication, magazinePages)
    libraryDatabase.addBook(magazine)

    // View available books
    libraryDatabase.viewAvailableBooks()

    // Search for a book
    println("Enter book title to search: ")
    val searchTitle = readLine() ?: ""
    libraryDatabase.searchForBook(searchTitle)

    // Lend a book to the user
    println("Enter the title of the book to borrow:")
    val borrowTitle = readLine() ?: ""
    val bookToBorrow = libraryDatabase.getAvailableBooks().find { it.title.equals(borrowTitle, ignoreCase = true) }

    if (bookToBorrow != null) {
        libraryDatabase.lendBook(bookToBorrow, user)
    } else {
        println("The book titled '$borrowTitle' is not available.")
    }

    // User returns a book
    println("Enter the title of the book to return:")
    val returnTitle = readLine() ?: ""
    val bookToReturn = libraryDatabase.getAvailableBooks().find { it.title.equals(returnTitle, ignoreCase = true) }

    if (bookToReturn != null) {
        libraryDatabase.receiveBookFromBorrower(bookToReturn, user)
    } else {
        println("The book titled '$returnTitle' was not borrowed by ${user.name}.")
    }

    // Final view of available books
    libraryDatabase.viewAvailableBooks()
}
