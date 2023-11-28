import java.util.*;
public class main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int arr[] = new int[n];
            int isSorted=1; //let's assume it sorted first
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
                if(i>0 && arr[i-1]>arr[i]) isSorted=0; 
            }
            if(isSorted==1) System.out.println(0);  //no operations required
            else{
                int minops=0;
                Stack<Integer> stack = new Stack<>();
                stack.push(arr[n-1]); //pushing the last elemnt for makign comparisons
                int index=n-2;
                while(index>-1){
                    int currel=arr[index];
                    if(stack.peek()>=currel) stack.push(index);
                    else{
                        int x=0;
                        while(currel>stack.peek()){
                            x=currel - stack.peek();
                            stack.push(currel-x);
                            minops++;
                            currel=x;
                        }
                        stack.push(x);
                    }
                    index--;
                }
                print(stack);
                System.out.println(minops);
            }
        }
        sc.close();
    }

    public static void print(Stack<Integer> s){
        while(!s.isEmpty()){
            System.out.print(s.pop());
            System.out.print(", ");
        }
        System.out.println();
    }
}


/*
After  alot of thinking I belive travelling backwards is more beneficial in this problem.
Felt so after seeing last testcase and trying to do it on my own.
Becuase this way we cna know how much smaller we can have it as an element.
Also, a use of a stack can come to mind.
So, that we cna always compare it with a backward element.
*/