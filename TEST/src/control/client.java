/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import view.Home;
import view.Login;
import view.BoardClient;
import view.BoardAI;
/**
 *
 * @author admin
 */
public class client {

    public static class RunClient {

        public RunClient() {
        }
    }
    public enum View{
        Login,
        Home,
        BoardAI,
        BoardClient,
        rea
        
    }
    //giao dien
  
    public static BoardAI boardAI;
    public static BoardClient boardClient;
    //tao socket
 
    public static void main(String[] args) {
        
    }

}
