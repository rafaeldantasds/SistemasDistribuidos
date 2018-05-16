/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author diego
 */
public class Server {
    
    public static void main(String[] args) {
        
        try{
        
            //Define a porta do servidor
            int port = 8080;

            //Inicia o servidor na porta escolhida / servidor começa a esperar por conexões e enfileirá-las
            ServerSocket serverSocket = new ServerSocket(port);
            
            System.out.println("Servidor iniciado na porta " + port + "...");

            //Laço para deixar o servidor sempre ativo aguardando por novas conexões
            while(true){
                
                //Recebe uma nova conexão
                Socket socket = serverSocket.accept();
                
                //Cria a thread da conexão passando o scoket criado como parametros
                Connection connection = new Connection(socket);
                
                //Inicia a thread
                connection.start();

            }
            
        }catch(IOException e){
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
}
