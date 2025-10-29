package ru.nwork.demoqa.api.util;

import ru.nwork.demoqa.api.models.Book;


public class BooksHelper {
    public static final Book book1 = new Book(
            "9781449325862",
            "Git Pocket Guide",
            "A Working Introduction",
            "Richard E. Silverman",
            "2020-06-04T08:48:39.000Z",
            "O'Reilly Media",
            234,
            "This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp",
            "http://chimera.labs.oreilly.com/books/1230000000561/index.html");

    public static final Book book2 = new Book(
            "9781449337711",
            "Designing Evolvable Web APIs with ASP.NET",
            "Harnessing the Power of the Web",
            "Glenn Block et al.",
            "2020-06-04T09:12:43.000Z",
            "O'Reilly Media",
            238,
            "Design and build Web APIs for a broad range of clients—including browsers and mobile devices—that can adapt to change over time. This practical, hands-on guide takes you through the theory and tools you need to build evolvable HTTP services with Microsoft",
            "http://chimera.labs.oreilly.com/books/1234000001708/index.html");

}

