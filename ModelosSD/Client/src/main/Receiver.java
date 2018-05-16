/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author diego
 */
public class Receiver extends Thread {
    
    private Socket socket;
    private BufferedReader in;
    
    public Receiver(Socket socket){
        
        //Armazena o socket recebido e cria o canal de entrada e saída de dados (comunicação do socket)
        try {
            
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        
        }catch(IOException e){
        
            System.out.println(e.getMessage());
        
        }
        
    }
    
    @Override
    public void run(){

        //Inicio do tratamento das requisições recebidas
        try{

            System.out.println("Receiver iniciado ...");
            
            String mensagem;
            
            //Aguarda (fica bloqueado) uma entrada de dados (string) / Atribui a mensagem recebida a váriavel mensagem 
            while((mensagem = in.readLine()) != null){
                        
                //Realiza o processamento necessário (mas não responde o servidor, pois as mensagens são enviadas apenas pela thread principal) ...
                System.out.println("Mensagem recebida de " + this.socket.getInetAddress().getHostAddress() + ":" + this.socket.getPort() + " : " + mensagem);
                        
            }
            
            //Encerra as conexões
            this.in.close();
            this.socket.close();
            
            System.out.println("Receiver encerrado ...");
            
        }catch(IOException e){
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
}
