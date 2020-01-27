/**
 * 给定一个链表，如果链表中存在环，则返回到链表中环的起始节点，如果没有环，返回null。
 *
 * 依旧是利用快慢指针，如果快慢指针重合说明有环，如果存在环，通过同时移动head和慢指针，当它们重合的时候，这就是环的起点
 * 假设链表非环部分的长度是x1, 环的长度是x2， slow的步长是d，fast的步长是2d，总共走了m步
 * 那么在slow和fast相遇时（L为slow和fast相遇之前的距离），前者走了md = x1 + L，后者走了2*md = x1 - 1 + x2 + L
 * 前面式子代入后面得x1 + 1 = x2 - L，（x1+1就是正好到环头，x2-L就是slow再走这么多步也到环头了）也就是说，head和slow要相遇的距离相差1
 * 也就是head走到环起始点时，slow肯定相差一个步长，因此head = slow.next就是循环出口
 */


public class _103_带环链表II {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        //依旧是判断有没有环，有环就进行处理,不过，有环要break掉
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //有环
        //证明：由于有环，必定是在环中某个位置相遇，假设非环链表的长度为x1，环的长度为x2，慢指针速度为1，快快指针速度为2，两者都走了m步
        //假设慢指针进入环中走过的距离为L才和快指针相遇，那么慢指针一共走过：x1+L = 1*m
        //快指针走过的距离为 x1 + x2 + L = 2m, 把前者带入 x1 + x2 + L = 2x1 + 2L，即 x2 = x1+L，x2 - L = x1，
        //也就是环的剩下部分等于非环链表的长度，所以head和slow一起走，两者碰头时，就是环的起点
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }


}