/**
 * 快慢指针
 *
 * 先让快指针走n步，慢指针再走，当快指针指向最后一个点的时候，慢指针指向的点就是待删除点的前一个点。（他们之间距离就是n～）
 * 要有一个头指针，dummy node, 以防删除的点是头
 *
 */
public class _174_删除链表中倒数第n个节点 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast.next != null) {
            //找到n的前一个，走n-1步
            if (n <= 0) {
                slow = slow.next;
            }
            fast = fast.next;
            n--;
        }
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }
}