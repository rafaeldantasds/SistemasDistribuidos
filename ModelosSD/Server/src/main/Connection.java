/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author diego
 */
public class Connection extends Thread {
    
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public Connection(Socket socket){
        
        //Armazena o socket recebido e cria os canais de entrada e saída de dados (comunicação do socket)
        try {
            
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);//true por causa do flush
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
        }catch(IOException e){
        
            System.out.println(e.getMessage());
        
        }
        
    }
    
    @Override
    public void run(){
        
        //Inicio do tratamento das requisições
        try{

            System.out.println("Nova conexão de " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
            
            String mensagem;
            
            //Aguarda (fica bloqueado) uma entrada de dados (string) / Atribui a mensagem recebida a váriavel mensagem 
            while((mensagem = in.readLine()) != null){
                        
                //Realiza o processamento necessário ...
                System.out.println("Mensagem recebida de " + this.socket.getInetAddress().getHostAddress() + ":" + this.socket.getPort() + " : " + mensagem);
                System.out.println("Mensagem enviada para " + this.socket.getInetAddress().getHostAddress() + ":" + this.socket.getPort() + " : " + mensagem.toUpperCase());
                       
                //Responde a requisiçao com a mensagem processada
                this.out.println(mensagem.toUpperCase());
            }
            
            //Encerra a comunicação e conexões
            this.in.close();
            this.out.close(); //Ao fechar o canal de saída de dados, automaticamente o canal de entrada (do cliente) recebe o valor null e sai do laço de espera de entrada de dados
            this.socket.close();
            
            System.out.println("Conexao encerrada para " + this.socket.getInetAddress().getHostAddress() + ":" + this.socket.getPort());
            
        }catch(IOException e){
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
}
