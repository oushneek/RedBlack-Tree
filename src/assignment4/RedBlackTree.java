
package assignment4;

import java.util.Scanner;

/**
 *
 * @author Tazbeea Tazakka
 */
public class RedBlackTree {

    Node rootNode;
    
    public static void main(String[] args) {
        
        new RedBlackTree().run();
        
    }

    private void print(Node node) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while(node!=null){
            System.out.println("Node= "+node.value+" "+node.color);
            if(node.left==null && node.right==null)
                break;
            print(node.left);
            print(node.right);
            
        }
    }

    
    static class Node{
        Node left;
        Node right;
        Node parent;
        Node grandparent;
        int value;
        String color;
        
        public Node(int value){
            this.value=value;
            this.color="red";
            this.left=null;
            this.right=null;       
        }   
    }
    
    public void run(){
        System.out.print("Insert = ");
        Scanner user_input=new Scanner(System.in);
        Integer input=user_input.nextInt();
        rootNode=new Node(input);
        rootNode.parent=null;
        rootNode.grandparent=null;
        rootNode.left=null;
        rootNode.right=null;
        modify(rootNode);
        
        
        while(true){
           System.out.print("Insert = ");
           input=user_input.nextInt();
           if(input==-1)
               break;
           else
            insert(rootNode,input);
           
        }       
    }

    /**
     *
     * @param node
     * @return
     */
    public Node copyNode(Node node){

        if(node!=null){
            Node temp=new Node(node.value);
            temp.color=node.color;
            temp.left=copyNode(node.left);
            temp.right=copyNode(node.right);
            temp.parent=copyNode(node.parent);
            temp.grandparent=copyNode(node.grandparent);
            return temp;
        }
        else
            return node;
        
    }

    private Node leftRotate(Node grandparent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
        Node temp;
       
        temp=copyNode(grandparent.left);
        grandparent.left=copyNode(grandparent.left.right);
        grandparent.left.parent=copyNode(grandparent);
        
        grandparent.left.left=copyNode(temp);
        grandparent.left.left.right=copyNode(temp.right.left);
        grandparent.left.left.parent=copyNode(grandparent.left);
        
        return grandparent.left;
    }
    public Node rightRotate(Node grandparent){
        Node temp;
        temp=copyNode(grandparent.left);
        grandparent.left=copyNode(grandparent.left.right);
        grandparent.left.parent=grandparent;
        
        temp.right=copyNode(grandparent);
        temp.right.parent=copyNode(temp);
        
        return temp;
    }
    private boolean compare(Node px,Node ppx){
        int px_val,ppx_val;
        px_val=px.value;
        ppx_val=ppx.value;
        return px==ppx;
    }
    private void modify(Node node) {
        if(node.parent!=null && node.parent.color.equals("red")){
            System.out.println("while works for "+node.value);
            /*if(compare(node.parent, node.grandparent.left)){            
                //if parent is left child of grandparent
                if(node.grandparent.right.color.equals("red")){                 //if color of uncle node is red
                    //case 1
                    //recolor
                    node.grandparent.color="red";
                    node.parent.color="black";
                    node.grandparent.right.color="black";
                    System.out.println();
                        //new node
                    node=copyNode(node.grandparent);
                }
                else if(compare(node,node.parent.right)){                       //else if node is right child of parent
                    //case 2 then case 3
                    //left-rotate(p(x))
                    node.grandparent.left=leftRotate(node.grandparent);
                    // NEW NODE (x)
                    node=copyNode(node.grandparent.left.left);
                    //right rotate(p(p(x))
                    node= rightRotate(node.grandparent);
                
                }
                
            }
            else{
                if(compare(node.parent, node.grandparent.right)){         //else if parent is right child of grandparent
                    if(node.grandparent.left.color.equals("red")){              //if color of uncle is red
                        //case 1
                        //recolor
                        node.grandparent.color="red";
                        node.parent.color="black";
                        node.grandparent.left.color="black";
                        //new node (x)
                        node=copyNode(node.grandparent);
                    }
                    else if(compare(node,node.parent.left)){                     //else if node is left child of parent
                        // case 2 and case 3
                        //right-rotate()
                        Node maketemp;
                        maketemp=copyNode(node.grandparent);
                        node.grandparent.right=rightRotate(node.parent);
                        //new node(x)
                        node=copyNode(maketemp.right.right);
                        //left-rotate()
                        
                        maketemp=copyNode(node);
                        node.parent=leftRotate(node.grandparent);
                        node=copyNode(maketemp.parent);
                        //recolor
                        node.color="black";
                        node.left.color="red";
                        node.right.color="red";
                        
                    }
                }
            }*/
        } // end of while loop
        // color root black
        //node.color="black";
        //System.out.println("Red-Black Tree");
        //print(node);
        
    }
    private void insert(Node node, int value){
        if(value<node.value){
            if(node.left!=null){
                insert(node.left,value);
            }
            else{           
                node.left=new Node(value);
                node.left.grandparent=node.parent;
                node.left.parent=node;
                node.left.left=null;
                node.left.right=null;
                //if(node.parent==null){
                    //System.out.println("Node= "+node.left.value+" "+node.left.color);
                //}
                //System.out.println("Node "+node.left.value+" left child of "+node.value);
                //else
                    modify(node.left);
            }
        }
        else{
            if(node.right!=null){
                insert(node.right,value);
            }
            else{
                
                node.right=new Node(value);
                node.right.grandparent=node.parent;
                node.right.parent=node;
                node.right.left=null;
                node.right.right=null;
                //if(node.parent==null){
                    //System.out.println("Node= "+node.right.value+" "+node.right.color);
                //}
                //System.out.println("Node "+node.right.value+" right child of "+node.value);
                //else
                    modify(node.right);
            }
        }
    }
}
