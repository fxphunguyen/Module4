package com.codegym.model;



import javax.persistence.*;

    @Entity
    @Table( name = "blogs")
    public class Blog {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private String content;

        public Blog() {

        }

        public Blog(Long id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        @Override
        public String toString() {
            return String.format("Blog[id=%d, title='%s', content='%s']", id, title, content);
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
}
