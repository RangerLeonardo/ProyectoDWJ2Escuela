package org.bedu.postwork.javase2project.controller;

import org.bedu.postwork.javase2project.async.SolicitudEstudiante;
import org.bedu.postwork.javase2project.service.NotificadorInscripcionService;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class SolicitudesController implements Runnable{
    private boolean enEjecucion = false;
    private Queue<SolicitudEstudiante> solicitudesPendientes = new LinkedList<>();
    private final NotificadorInscripcionService notificadorMaestro;

    public SolicitudesController(NotificadorInscripcionService notificadorMaestro  ) {
        this.notificadorMaestro = notificadorMaestro;
    }
    public void iniciar(){
        this.enEjecucion = true;
        new Thread(this).start();
    }
    public void detener(){
        this.enEjecucion = false;
    }

    public void registrarEvento(SolicitudEstudiante evento){
        solicitudesPendientes.add(evento);
    }

    @Override
    public void run() {
        try{
            while(enEjecucion || !solicitudesPendientes.isEmpty()){
                SolicitudEstudiante solicitud = solicitudesPendientes.poll();

                if(solicitud == null){
                    System.out.println("Esperando solicitudes nuevas...");
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                notificadorMaestro.notificadorMaestro(solicitud);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
