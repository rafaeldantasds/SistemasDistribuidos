/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Client {
    
    public static void main(String[] args) {
        
        try {
            
            //Ip e porta do servidor
            String ip = "127.0.0.1";
            int port = 8080;

            //Inicia uma nova conexão com o servidor
            Socket socket = new Socket(InetAddress.getByName(ip), port);
            
            System.out.println("Conexão iniciada com " + ip + ":" + port + "...");
            
            //Cria a thread que vai receber os dados do servidor e passa o socket como parametro
            Receiver receiver = new Receiver(socket);
            //Inicia a thread
            receiver.start();
            
            //Cria o canal de saída de dados (envia dados para o servidor)
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            //Classe para leitura de teclado
            Scanner scanner = new Scanner(System.in);
            String mensagem;
            
            //A cada mensagem digitada envia dados para o servidor
            while(!(mensagem = scanner.next()).equals("sair")){
                
                //Envia a mensagem digitada para o servidor (a resposta será captada pelo receiver)
                out.println(mensagem);
                System.out.println("Mensagem enviada para " + ip + ":" + port + " : " + mensagem);
                
            }
            
            //Encerra as conexões
            out.close(); //Ao fechar o canal de saída de dados, automaticamente o canal de entrada (do servidor) recebe o valor null e sai do laço de espera de entrada de dados
            socket.close();
            
            System.out.println("Conexão encerrada com " + ip + ":" + port + "...");
            
        }catch(IOException e){
         
            System.out.println(e.getMessage());
            
        }
        
        
        
    }
    
}
