package cfx;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2022-05-27 13:36
 * @Description:
 */
class BinTree {
    private TreeNode node;


    class TreeNode {

        private int data;

        private TreeNode left;

        private TreeNode right;


        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode() {
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }




    }
}