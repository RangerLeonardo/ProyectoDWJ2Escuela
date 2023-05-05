package org.bedu.postwork.javase2project.tools;

import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
/**
 * Esta clase implementa el algoritmo de Merge Sort para ordenar una lista de elementos.
 */
public class MergeSortAlgorithm {

    /**
     * Ordena la lista dada usando el ordenamiento natural de sus elementos.
     *
     * @param listaDesordenada lista a ordenar
     * @param <T>          el tipo de elementos que serán comparados
     * @return la lista ordenada
     */
    public static <T extends Comparable<T>> List<T> sort(List<T> listaDesordenada) {
        return sort(listaDesordenada, Comparator.naturalOrder());
    }

    /**
     * Ordena la lista usando el comparador provisto.
     *
     * @param listaDesordenada la lista a ordenar
     * @param comparador   el comparador a usar para ordenar la lista
     * @param <T>          el tipo de elementos en la lista
     * @return la lista ordenada
     */
    public static <T> List<T> sort(List<T> listaDesordenada, Comparator<T> comparador) {
        if (listaDesordenada.size() <= 1) {
            return listaDesordenada;
        }

        int mitad = listaDesordenada.size() / 2;

        List<T> izquierda = listaDesordenada.subList(0, mitad);
        List<T> derecha = listaDesordenada.subList(mitad, listaDesordenada.size());

        List<T> ordenIzquierdo = sort(izquierda, comparador);
        List<T> ordenDerecho = sort(derecha, comparador);

        return unionListas(ordenIzquierdo, ordenDerecho, comparador);
    }

    /**
     * Combina las dos listas para crear una sola.
     *
     * @param izquierda       la lista ordenada de la izquierda
     * @param derecha      la lista ordenada de la derecha
     * @param comparador el comparador a usar para ordenar la lista
     * @param <T>        el tipo de elementos en la lista
     * @return la lista ordenada
     */
    private static <T> List<T> unionListas(List<T> izquierda, List<T> derecha, Comparator<T> comparador) {
        List<T> merged = new ArrayList<>(izquierda.size() + derecha.size());

        int indiceIzq = 0;
        int indiceDer = 0;

        while (indiceIzq < izquierda.size() && indiceDer < derecha.size()) {
            T elementoIzq = izquierda.get(indiceIzq);
            T elementoDer = derecha.get(indiceDer);

            if (comparador.compare(elementoIzq, elementoDer) <= 0) {
                merged.add(elementoIzq);
                indiceIzq++;
            } else {
                merged.add(elementoDer);
                indiceDer++;
            }
        }

        merged.addAll(izquierda.subList(indiceIzq, izquierda.size()));
        merged.addAll(derecha.subList(indiceDer, derecha.size()));

        return merged;
    }
}


