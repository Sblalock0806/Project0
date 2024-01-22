package org.example.Model;

import java.sql.Timestamp;

public class BookEntry {

    public class NoteEntry {
        private String bookTitle;
        private String authorFname;
        private String authorLname;
        private Timestamp checkInTime;
        private Timestamp checkOutTime;
        private String bookStatus;

        public NoteEntry(String bookTitle, String authorFname, String authorLname)
        {
            this.bookTitle = bookTitle;
            this.authorFname = authorFname;
            this.authorLname = authorLname;
// if I don't have a timestamp provided, I have a choice (as a developer) to choose
// between leaving time null or setting the time to the current time here.
        }

        public NoteEntry(String bookTitle, String authorFname, String authorLname, Timestamp checkInTime, Timestamp checkOutTime, String bookStatus )
        {
            this.bookTitle = bookTitle;
            this.authorFname = authorFname;
            this.authorLname = authorLname;
            this.checkInTime = checkInTime;
            this.checkOutTime = checkOutTime;
            this.bookStatus = bookStatus;
        }

        public void setBookTitle(String bookTitle)
        {
            this.bookTitle = bookTitle;
        }

        public void setauthorFname(String authorFname)
        {
            this.authorFname = authorFname;
        }

        public void setauthorLname(String authorLname)
        {
            this.authorLname = authorLname;
        }

        public void setcheckInTime(Timestamp checkInTime)
        {
            this.checkInTime = checkInTime;
        }
        public void secheckOutTime(Timestamp checkOutTime)
        {
            this.checkOutTime = checkOutTime;
        }

        public void setbookStatus(String bookStatus)
        {
            this.bookStatus = bookStatus;
        }

        public String setBookTitle()
        {
            return bookTitle;
        }

        public String setauthorFname()
        {
            return authorFname;
        }

        public String setauthorLname()
        {
            return authorLname;
        }
        public Timestamp setcheckInTime()
        {
            return checkInTime;
        }

        public Timestamp setcheckOutTime()
        {
            return checkOutTime;
        }

        public String setbookStatus()
        {
            return bookStatus;
        }

        @Override
        public String toString() {
            return "NoteEntry{" +
                    "bookTitle='" + bookTitle + '\'' +
                    ", authorFname='" + authorFname + '\'' +
                    ", authorLname='" + authorLname + '\'' +
                    ", checkInTime=" + checkInTime +
                    ", checkOutTime=" + checkOutTime +
                    ", bookStatus='" + bookStatus + '\'' +
                    '}';
        }
    }
    }


