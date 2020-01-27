/**
 * 翻转链表
 * 同向双指针在链表中的应用。
 * 1 -> 2 -> 3 -> null
 * 首先要改成1 <- 2 -> 3,然后两个指针往下走，但这样指了后，2的下一个是谁不就不知道了吗？
 * 所以要先把2的下一个先存起来，再改变指向，再往后走。
 * 初始条件，1的前面是空的。
 */
public class _035_翻转链表 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    //非递归
    public ListNode reverse2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //首先把当前节点的下一个存下来
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //递归
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //自顶向下，把head的后面的头传进去翻转，得到的是翻转链表的尾巴，后面链表翻转完的尾巴就是head.next
        ListNode tail = reverse(head.next);
        //翻转最后一个head。由于链表翻转完的尾巴就是head.next，要让head变为最后一个，那就是head.next.next = head
        head.next.next = head;
        //别忘了当前的头现在得指向null
        head.next = null;
        return tail;
    }

}