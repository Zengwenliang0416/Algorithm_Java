public class Day01 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);//创建虚拟头节点
        dummyHead.next = head;//将链表链接到虚拟头节点
        ListNode temp = dummyHead;//创建临时链表存放新的链表
        while (temp.next != null) {//判断循环是否执行结束
            if (temp.next.val == val) {//判断是否达到执行条件
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;
        while (fast != null) {
            if (n>=0){
                fast = fast.next;
                n--;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }


    public static void main(String[] args) {

    }
}
