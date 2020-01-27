/**
 * 翻转链表中第m个节点到第n个节点的部分
 *
 * 要翻转第m个到第n个节点，首先要找到第m个节点的前驱节点
 * 然后翻转中间那部分，最后动个头和尾
 */
public class _036_翻转链表II {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n || head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //从dummy开始
        head = dummy;
        //找到第m-1个节点
        for (int i = 1; i < m; i++) {
            if (head.next == null) {
                return null;
            }
            head = head.next;
        }
        //此时head指向第m-1个节点
        ListNode preNode = head;
        ListNode mNode = head.next;
        ListNode nNode = mNode;
        ListNode postNode = mNode.next;

        //nNode就相当于翻转链表的head了，不要动head，开始反转,postNode相当于cur，nNode相当于pre
        for (int i = m; i < n; i++) {
            if (postNode != null) {
                ListNode temp = postNode.next;
                postNode.next = nNode;
                //移动
                nNode = postNode;
                postNode = temp;
            }
        }
        //此时nNode指向翻转后的头，也就是尾巴,他要变为翻转后的头，然后原来的头变为尾巴，后面置为postNode，而不是null，因为n节点后面可能还有
        mNode.next = postNode;
        preNode.next = nNode;
        return dummy.next;
    }
}