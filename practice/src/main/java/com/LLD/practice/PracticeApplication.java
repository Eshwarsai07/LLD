package com.LLD.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PracticeApplication {
	
	static {
		System.out.println("static block in PraticeApplication");
	}

	static class Employee {
		int salary;
		String name;

		@Override
		public String toString() {
			return name;
		}

		public Employee(int salary, String name) {
			this.name = name;
			this.salary = salary;
		}

		public Employee(String name, int salary) {
			this.name = name;
			this.salary = salary;
		}
	}

	static class Transaction {
		String date;
		int amnt;
		
		static {
			System.out.println("static block in transaction");
		}

		public Transaction(String date, int amnt) {
			this.date = date;
			this.amnt = amnt;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getAmnt() {
			return amnt;
		}

		public void setAmnt(int amnt) {
			this.amnt = amnt;
		}

	}

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {

//		Class c = Class.forName("com.LLD.practice.PracticeApplication");
//		Class c1 = PracticeApplication.class;
		
		List<Employee> empList = new ArrayList();
		empList.add(new Employee(30, "Eshwar"));
		empList.add(new Employee(40, "sai"));
		empList.add(new Employee(10, "abc"));
		empList.add(new Employee(5, "def"));

		Optional<Employee> thirdHigh = empList.stream().sorted((a, b) -> {
			if (b.salary > a.salary)
				return 1;
			else if (b.salary < a.salary)
				return -1;
			else
				return 0;
		}).skip(2).findFirst();
		List<Employee> empSalariesLessThanThirdHighest = empList.stream().filter(e -> e.salary < thirdHigh.get().salary)
				.collect(Collectors.toList());
		System.out.println(empSalariesLessThanThirdHighest);

		int[] arr = { 1, 2, 3, 4, 5 };
		OptionalInt total = Arrays.stream(arr).reduce((a, b) -> a + b);
		System.out.println(total.getAsInt());

		List<String> strings = Arrays.asList("apple", "banana", "cherry", "date", "grapefruit");

		Optional<String> s = strings.stream().max(Comparator.comparingInt(String::length));
		System.out.println(s.get());

		List<Employee> employees = Arrays.asList(new Employee("Alice", 25), new Employee("Bob", 30),
				new Employee("Charlie", 35));

//		mapToInt is used to convert Stream<Employee> to IntStream which can operate on stream of int values. and average() is present in IntStream
		System.out.println(employees.stream().mapToInt(value -> value.salary).average().orElse(0));

		new PracticeApplication().printPrime();

		List<Integer> list1 = List.of(1, 0, 2, 0, 3, 0, 4);
		Stream s1 = list1.stream().filter(num -> num != 0);
		Stream s2 = list1.stream().filter(num -> num == 0);
		Stream concatStream = Stream.concat(s1, s2);
		System.out.println(concatStream.collect(Collectors.toList()));

		List<Integer> list11 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
		List<Integer> intersection = list11.stream().filter(element -> list2.contains(element))
				.collect(Collectors.toList());

		System.out.println(intersection);

		List<Transaction> transactions = Arrays.asList(new Transaction("2022-01-01", 100),
				new Transaction("2022-01-01", 200), new Transaction("2022-01-02", 300),
				new Transaction("2022-01-02", 400), new Transaction("2022-01-03", 500));

		Map<String, Integer> sumByDay = transactions.stream()
				.collect(Collectors.groupingBy(Transaction::getDate, Collectors.summingInt(Transaction::getAmnt)));

		System.out.println(sumByDay);

		List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
		Map<String, Long> wordFrequency = words.stream()
				.collect(Collectors.groupingBy(element -> element, Collectors.counting()));

		System.out.println(wordFrequency);

//		******************************************************************************************
//		Find frequency of each word in a sentence

		String str = "Java is a programming language Java is platform independent";
		Map<String, Long> m = Arrays.stream(str.split("\\s+"))
				.collect(Collectors.groupingBy(word -> word.toLowerCase(), Collectors.counting()));
		System.out.println(m);

//		******************************************************************************************
//		List of integers starting with 1

		List<Integer> l = List.of(1, 12, 3, 43, 15);
		List<Integer> startsWithOne = l.stream().filter(num -> String.valueOf(num).startsWith("1"))
				.collect(Collectors.toList());
		System.out.println(startsWithOne);

//		******************************************************************************************
//		Given list of names group them by there first letter, and count number of names in each group
		String[] names = { "Eshwar", "Sai", "Esh", "Suraj", "Ganesh" };
		Map<Character, Long> groupedNyFirstCharacter = Arrays.stream(names)
				.collect(Collectors.groupingBy(name -> name.charAt(0), Collectors.counting()));
		System.out.println(groupedNyFirstCharacter);

//		******************************************************************************************
//		Find and print duplicate numbers in an array
		int[] nums = { 1, 2, 3, 3, 4, 5, 6, 6, 1, 7 };
		Arrays.stream(nums).boxed().collect(Collectors.groupingBy(num -> num, Collectors.counting())).entrySet()
				.stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey())
				.forEach(entryKey -> System.out.print(entryKey + " "));

//		******************************************************************************************
//		Given list of words print all palindroms

		String[] listOfWords = { "level", "radar", "ganga", "deed" };
		System.out.println();
		List<String> palindromWords = Arrays.stream(listOfWords)
				.filter(word -> word.equals(new StringBuilder(word).reverse().toString())).collect(Collectors.toList());
		System.out.println(palindromWords);

//		******************************************************************************************
//		Merge 2 sorted list into single list
		int[] sortList1 = { 1, 3, 5, 7, 9 };
		int[] sortList2 = { 2, 4, 6, 8 };
		int[] sortedList = IntStream.concat(Arrays.stream(sortList1), Arrays.stream(sortList2)).sorted().toArray();
		System.out.println(Arrays.toString(sortedList));

//		******************************************************************************************
//		Employee classification based on salary

		List<Employee> emps = List.of(new Employee(10, "Eshwar"), new Employee(9, "Sai"), new Employee(11, "Ganesh"),
				new Employee(12, "Chettipalli"));

		Map<String, List<Employee>> empClassification = emps.stream()
				.collect(Collectors.groupingBy(emp -> emp.salary <= 10 ? "Assosiate" : "Senior Assosiate"));
		System.out.println(empClassification);

//		******************************************************************************************
//		List of strings arrange in increasing order of there length

		String[] namesIncreasing = { "Eshwar", "sai", "Ganesh" };
		List<String> sortedStringList = Arrays.stream(namesIncreasing).sorted(Comparator.comparingInt(String::length))
				.collect(Collectors.toList());
		System.out.println(sortedStringList);
	}

	public boolean isPrime(int num) {
		if (num <= 1)
			return false;
		for (int i = 2; i < Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	private void printPrime() {
		List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);
		boolean containsPrime = numbers.stream().anyMatch(this::isPrime);
		System.out.println("List contains a prime number: " + containsPrime);

//		Calendar c = new Calendar();

	}

}
