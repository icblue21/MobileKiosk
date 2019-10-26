package com.example.mobilekiosk;


class QNode {
    String item, count, price;
    QNode next;

    public QNode(String item, String count, String price)
    {
        this.item = item;
        this.count = count;
        this.price = price;
        this.next = null;
    }
}

class myQueue {
    QNode front, rear;

    public myQueue()
    {
        this.front = this.rear = null;
    }

    void enqueue(String item, String count, String price)
    {
        QNode temp = new QNode(item, count, price);

        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        this.rear.next = temp;
        this.rear = temp;
    }

    QNode dequeue()
    {
        if (this.front == null)
            return null;

        QNode temp = this.front;
        this.front = this.front.next;

        if (this.front == null)
            this.rear = null;
        return temp;
    }
    String printAll() {
        QNode temp = front;
        String ret = "";
        while(temp != null) {
            ret += (temp.item + temp.count + "개 " + temp.price + "원\n");
            temp = temp.next;
        }
        return ret;
    }

    void changeValue(int idx, String count, String price) {
        //idx번째 요소변경
        QNode temp = front;
        for(int i = 0; i < idx; i++) {
            temp = temp.next;
        }
        temp.count = count;
        temp.price = price;
    }

    void delete(int idx) {
        QNode temp = front;
        QNode prev, after;
        if(idx == 0) {
            temp = front.next;
            front.next = null;
            front = temp;
        }
        else {
            for(int i = 0; i < idx-1; i++) {
                temp = temp.next;
            }
            if(temp.next == rear) {
                prev = temp;
                temp = temp.next;
                prev.next = null;
            }
            else {
                prev = temp;
                temp = temp.next;
                after = temp.next;
                prev.next = after;
                temp.next = null;
            }
        }
    }
}





