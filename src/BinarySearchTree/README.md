# Binary Search Trees

A basic implementation of binary trees. When I implemented stacks,
I created a class Stack, which had an inner class Stack.Item.  An
object of the Stack class could represent any stack, empty or full.
Here, I've taken a different approach: an object of the BinaryTree
represents a tree with at least one node; an empty tree (one with
no nodes) would be represented by just a null reference.  Each
BinaryTree object represents a tree, with the value stored at its
root and the left and right subtrees.
