package com.mike.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.mike.curso.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

	@Autowired
    private StudentRepository studentRepository;

	@Autowired
    private CourseRepository courseRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        manyToManyRemoveFindBidirectional();
    }

    @Transactional
	public void manyToManyRemoveFindBidirectional(){

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();
		
		student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

        Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);

		if(studentOptionalDb.isPresent()){

			Student studentDb = studentOptionalDb.get();

			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(1L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();

				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);

				System.out.println(studentDb);
			}

		}



	}


    @Transactional
	public void manyToManyFindBidirectional(){

		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findOneWithStudents(1L).get();
		Course course2 = courseRepository.findOneWithStudents(2L).get();
		
		student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


	}

     @Transactional
	public void manyToManyRemoveBidirectional(){

		Student student1 = new Student("Granu", "Lloron");
		Student student2 = new Student("Lucky", "Peleon");

		Course course1 = new Course("Lina", "Curso de no llorar");
		Course course2 = new Course("Mike", "Curso de no pelear");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
        
        Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);

		if(studentOptionalDb.isPresent()){

			Student studentDb = studentOptionalDb.get();

			Optional<Course> courseOptionalDb = courseRepository.findOneWithStudents(3L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();

				studentDb.removeCourse(courseDb);

				studentRepository.save(studentDb);

				System.out.println(studentDb);
			}

		}

	}

    @Transactional
	public void manyToManyBidirectional(){

		Student student1 = new Student("Granu", "Lloron");
		Student student2 = new Student("Lucky", "Peleon");

		Course course1 = new Course("Lina", "Curso de no llorar");
		Course course2 = new Course("Mike", "Curso de no pelear");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

        student1.addCourse(course1);
        student1.addCourse(course2);
        student2.addCourse(course1);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


	}

	@Transactional
	public void manyToManyRemove(){

		Student student1 = new Student("Rallas", "Lloron");
		Student student2 = new Student("Negro", "Peleon");

		Course course1 = new Course("Pepa", "Curso de no desesperar");
		Course course2 = new Course("Lula", "Curso de no auyar");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);

		if(studentOptionalDb.isPresent()){

			Student studentDb = studentOptionalDb.get();

			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();

				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);

				System.out.println(studentDb);
			}

		}



	}

	@Transactional
	public void manyToManyRemoveFind(){

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();
		
		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);

		if(studentOptionalDb.isPresent()){

			Student studentDb = studentOptionalDb.get();

			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if(courseOptionalDb.isPresent()){
				Course courseDb = courseOptionalDb.get();

				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);

				System.out.println(studentDb);
			}

		}


	}

	@Transactional
	public void manyToManyFind(){

		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();
		
		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


	}

	@Transactional
	public void manyToMany(){

		Student student1 = new Student("Granu", "Lloron");
		Student student2 = new Student("Lucky", "Peleon");

		Course course1 = new Course("Lina", "Curso de no llorar");
		Course course2 = new Course("Mike", "Curso de no pelear");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);


	}

    @Transactional
    public void oneToOneBidirectionalFindById() {

        Optional<Client> clientOptional = clientRepository.findOne(2L);

        clientOptional.ifPresent(client -> {

            ClientDetails clientDetails = new ClientDetails(5000, true);

            client.setClientDetails(clientDetails);
            clientDetails.setClient(client);

            clientRepository.save(client);

            System.out.println(client);
        });

    }

    @Transactional
    public void oneToOneBidirectional() {

        Client client = new Client("Ronaldhino", "Gaucho");
        ClientDetails clientDetails = new ClientDetails(5000, true);

        client.setClientDetails(clientDetails);
        clientDetails.setClient(client);

        clientRepository.save(client);

        System.out.println(client);

    }

    @Transactional
    public void oneToOneFindById() {
        ClientDetails clientDetails = new ClientDetails(5000, true);

        clientDetailsRepository.save(clientDetails);

        Optional<Client> clientOptional = clientRepository.findOne(2L);//new Client("Ronaldhino", "Gaucho");

        clientOptional.ifPresent(client -> {
            client.setClientDetails(clientDetails);

            System.out.println(client);
        });

    }

    @Transactional
    public void oneToOne() {
        ClientDetails clientDetails = new ClientDetails(5000, true);

        clientDetailsRepository.save(clientDetails);

        Client client = new Client("Ronaldhino", "Gaucho");
        client.setClientDetails(clientDetails);
        clientRepository.save(client);

        System.out.println(client);

    }

    @Transactional
    public void removeInvoiceBidiractionalFindById() {

        Optional<Client> optionalClient = clientRepository.findOne(2L);

        optionalClient.ifPresent(client -> {

            Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
            Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

            client.addInvoice(invoice1).addInvoice(invoice2);

            clientRepository.save(client);

            System.out.println(client);

        });

        Optional<Client> optionalClientDb = clientRepository.findOne(2L);

        optionalClientDb.ifPresent(client -> {

            Optional<Invoice> invOptional = invoiceRepository.findById(2L);
            invOptional.ifPresent(invoice -> {
                client.getInvoices().remove(invoice);
                invoice.setClient(null);

                clientRepository.save(client);
                System.out.println(client);
            });

        });

    }

    @Transactional
    public void oneToManyInvoiceBidiractionalFindById() {

        Optional<Client> optionalClient = clientRepository.findOne(2L);

        optionalClient.ifPresent(client -> {

            Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
            Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

            client.addInvoice(invoice1).addInvoice(invoice2);

            clientRepository.save(client);

            System.out.println(client);

        });

    }

    @Transactional
    public void oneToManyInvoiceBidiractional() {
        Client client = new Client("Frank", "Mora");

        Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
        Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

        client.addInvoice(invoice1).addInvoice(invoice2);

        clientRepository.save(client);

        System.out.println(client);

    }

    @Transactional
    public void removeAddressFindById() {

        Optional<Client> optionalClient = clientRepository.findById(2L);

        optionalClient.ifPresent(client -> {
            Address address1 = new Address("Calle", 19);
            Address address2 = new Address("carrera", 96);

            Set<Address> addresses = new HashSet<>();

            addresses.add(address1);
            addresses.add(address2);

            client.setAddresses(addresses);

            clientRepository.save(client);

            System.out.println(client);

            Optional<Client> optionalClient2 = clientRepository.findOne(2L);

            optionalClient2.ifPresent(c -> {
                c.getAddresses().remove(address1);
                clientRepository.save(c);

                System.out.println(c);
            });
        });

    }

    @Transactional
    public void removeAddress() {

        Client client = new Client("Frank", "Mora");

        Address address1 = new Address("Calle", 19);
        Address address2 = new Address("carrera", 96);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        clientRepository.save(client);

        System.out.println(client);

        Optional<Client> optionalClient = clientRepository.findById(3L);

        optionalClient.ifPresent(c -> {
            c.getAddresses().remove(address1);

            clientRepository.save(c);

            System.out.println(c);
        });

    }

    @Transactional
    public void oneToManyFindById() {

        Optional<Client> optionalClient = clientRepository.findById(2L);

        optionalClient.ifPresent(client -> {
            Address address1 = new Address("Calle", 19);
            Address address2 = new Address("carrera", 96);

            Set<Address> addresses = new HashSet<>();

            addresses.add(address1);
            addresses.add(address2);

            client.setAddresses(addresses);

            clientRepository.save(client);

            System.out.println(client);
        });

    }

    @Transactional
    public void oneToMany() {
        Client client = new Client("Frank", "Mora");

        Address address1 = new Address("Calle", 19);
        Address address2 = new Address("carrera", 96);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        clientRepository.save(client);

        System.out.println(client);

    }

    @Transactional
    public void manyToOne() {

        Client client = new Client("John", "Sanchez");
        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2000L);
        invoice.setClient(client);

        Invoice invoiceDb = invoiceRepository.save(invoice);

        System.out.println("Invoice: " + invoiceDb);

    }

    @Transactional
    public void manyToOneFindCLienById() {

        Optional<Client> optClient = clientRepository.findById(1L);

        if (optClient.isPresent()) {
            Client client = optClient.orElseThrow();

            Invoice invoice = new Invoice("Compras de oficina", 2000L);
            invoice.setClient(client);

            Invoice invoiceDb = invoiceRepository.save(invoice);

            System.out.println("Invoice: " + invoiceDb);

        }

    }

}
