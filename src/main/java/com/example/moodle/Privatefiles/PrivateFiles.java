package com.example.moodle.Privatefiles;
import java.io.Serializable;
public class PrivateFiles {

    public class filesdetails implements Serializable {

        private Integer id;
        private String name;
        private String size;
        private String type;
        private String path;

        private Byte synced;


        public filesdetails() {
        }

        public filesdetails(String name) {
            this.name = name;
        }

        public filesdetails(String name, String size, String type, String path) {
            this.name = name;
            this.size = size;
            this.type = type;
            this.path = path;
        }

        public Integer getId() {
            return this.id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return this.size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPath() {
            return this.path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

}
