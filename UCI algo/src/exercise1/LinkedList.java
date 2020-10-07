package exercise1;

public class LinkedList {
    ListNode head;
    public  LinkedList(){
        head=null;
    }
    public void add(int val){
        ListNode newNode=new ListNode(val);
        ListNode curr=head;
        if (head==null){
            head=newNode;
            return;
        }
        while(curr.next!=null){
            curr=curr.next;
        }
        curr.next=newNode;

    }
        public static ListNode reverse(ListNode head){
            ListNode dummy=new ListNode (-1);
            dummy.next=head;
            ListNode curr=head;
            ListNode pre=null;
            while(curr!=null){
                ListNode temp=curr.next;
                curr.next=pre;
                pre=curr;
                curr=temp;
            }
            return pre;
        }
        public static ListNode findMiddle(ListNode head){
            //printList(head);
            ListNode slow=head;
            ListNode fast=head;

            while(fast!=null&&fast.next!=null){//comment : if the length is odd, find the n/2+1;
                slow=slow.next;
                fast=fast.next.next;

            }

            return slow;
        }
        public static void printList(ListNode head){
            ListNode curr=head;
            while(curr!=null){
                System.out.print(curr.val+"->");
                curr=curr.next;
            }
        }

    public static void main(String[] args) {
        LinkedList list=new LinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        printList(list.head);
        ListNode newhead= reverse(list.head);
        printList(newhead);
        ListNode temp=findMiddle(newhead);
        printList(temp);
        System.out.println(temp.val);
        //System.out.println(findMiddle(newhead).val);

    }
}
class ListNode{
    int val;
    ListNode next;
    public  ListNode(int val){
        this.val=val;
    }
}