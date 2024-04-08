/*
∗ @file: MySquare.java
∗ @description: This file implements MySquare Class with a comparable data type.
∗ @author: Julia Jiang
∗ @date: January 22, 2024
∗ @acknowledgement : worked with Caroline Wales
*/

public class MySquare implements Comparable<MySquare>{
        //attributes:
        private int x;
        private int y;
        private String name;
        private int sideLength;

        //implement the constructor
        public MySquare (int sidel){
            x = 0;
            y = 0;
            name = "Square ";
            sideLength = sidel;
        }

        // This method returns the area of MySquare.
        public int getArea() {return sideLength * sideLength;}

        //This method compares the area of one MySquare to the other.
        @Override
        public int compareTo(MySquare other) {
                // Compare squares based on their areas
                return Integer.compare(this.getArea(), other.getArea());
        }

        // This method compares the name and content of Square with another.
        public boolean equals(Object obj) {
                if (this == obj) {
                        return true;
                }

                if (obj == null || getClass() != obj.getClass()) {
                        return false;
                }
                MySquare other = (MySquare) obj;

                return (this.name.equals(other.name) &&
                        this.x == other.x &&
                        this.y == other.y &&
                        this.sideLength == other.sideLength);
        }
}
