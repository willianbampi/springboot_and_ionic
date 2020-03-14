package com.cursomc.resources.utils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static String decodeParam(String input) {
		try {
			return URLDecoder.decode(input, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String input){
		/*
		String[] vet = input.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		*/
		return Arrays.asList(input.split(",")).stream().map(element -> Integer.parseInt(element)).collect(Collectors.toList());
	}
	
}