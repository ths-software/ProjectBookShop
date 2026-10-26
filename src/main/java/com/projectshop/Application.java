package com.projectshop;

import java.util.Arrays;
import java.util.List;

import com.projectshop.models.Stock;
import com.projectshop.security.Roles;
import com.projectshop.services.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import com.projectshop.models.AppUser;
import com.projectshop.models.Product;
import com.projectshop.services.ProductService;
import com.projectshop.services.UserService;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    CommandLineRunner initData(UserService userService, ProductService productService, StockService stockService) {
		return args -> {

			AppUser user2 = new AppUser("ac2", "apellido2", "ac2@gmail.com",
					"ac2", Roles.USER);
			user2 = userService.userRegister(user2);

			AppUser user1 = new AppUser("ac1", "apellido1", "ac1@gmail.com",
					"ac1", Roles.OWNER);
			user1 = userService.userRegister(user1);

			AppUser user3 = new AppUser("ac3", "apellido1", "ac3@gmail.com",
					"ac3", Roles.ADMIN);
			user3 = userService.userRegister(user3);


// ============================================================
// BOOK PRODUCTS
// ============================================================

			Product libro1 = new Product(
					"The Hobbit",
					18.99f,
					"Bilbo Bolsón abandona la tranquilidad de la Comarca para acompañar a un grupo de enanos en una aventura destinada a recuperar un tesoro custodiado por un dragón.",
					"/files/1.jpg",
					0
			);

			Product libro2 = new Product(
					"The Lord of the Rings",
					29.99f,
					"Frodo Bolsón emprende un peligroso viaje para destruir el Anillo Único y evitar que Sauron someta la Tierra Media.",
					"/files/2.jpg",
					0
			);

			Product libro3 = new Product(
					"Harry Potter y la piedra filosofal",
					19.99f,
					"Harry descubre que es un mago y comienza sus estudios en Hogwarts, donde conocerá amigos y secretos relacionados con su pasado.",
					"/files/3.jpg",
					0
			);

			Product libro4 = new Product(
					"Harry Potter y la cámara secreta",
					19.99f,
					"Durante su segundo curso en Hogwarts, Harry investiga una serie de ataques misteriosos relacionados con una antigua cámara.",
					"/files/4.jpg",
					0
			);

			Product libro5 = new Product(
					"Harry Potter y el prisionero de Azkaban",
					19.99f,
					"Harry afronta un nuevo curso en Hogwarts mientras intenta descubrir la verdad sobre Sirius Black y los acontecimientos que marcaron a sus padres.",
					"/files/5.jpg",
					0
			);

			Product libro6 = new Product(
					"Don Quijote de la Mancha",
					16.50f,
					"Alonso Quijano adopta la identidad de don Quijote y recorre los caminos junto a Sancho Panza, confundiendo a menudo la realidad con los libros de caballerías.",
					"/files/6.jpg",
					0
			);

			Product libro7 = new Product(
					"1984",
					14.99f,
					"Winston Smith vive en una sociedad totalitaria donde el Partido controla la información, la intimidad y hasta el pensamiento de sus ciudadanos.",
					"/files/7.jpg",
					0
			);

			Product libro8 = new Product(
					"Rebelión en la granja",
					12.99f,
					"Los animales de una granja se rebelan contra sus dueños, pero el nuevo orden acaba reproduciendo los abusos que pretendían eliminar.",
					"/files/8.jpg",
					0
			);

			Product libro9 = new Product(
					"Orgullo y prejuicio",
					13.99f,
					"Elizabeth Bennet y Fitzwilliam Darcy deben superar sus primeras impresiones y los prejuicios sociales para comprender sus propios sentimientos.",
					"/files/9.jpg",
					0
			);

			Product libro10 = new Product(
					"Frankenstein",
					12.50f,
					"Victor Frankenstein crea una criatura mediante un experimento científico y debe afrontar las consecuencias de su ambición.",
					"/files/10.jpg",
					0
			);

			Product libro11 = new Product(
					"Drácula",
					13.50f,
					"Un grupo de personas intenta detener al conde Drácula, una presencia sobrenatural que amenaza con extender su influencia más allá de Transilvania.",
					"/files/11.jpg",
					0
			);

			Product libro12 = new Product(
					"El principito",
					10.99f,
					"Un piloto conoce a un pequeño príncipe que relata sus viajes por distintos planetas y comparte reflexiones sobre la amistad, el amor y la vida.",
					"/files/12.jpg",
					0
			);

			Product libro13 = new Product(
					"Dune",
					22.99f,
					"Paul Atreides llega al desértico planeta Arrakis, donde las luchas políticas y el control de la especia cambiarán su destino.",
					"/files/13.jpg",
					0
			);

			Product libro14 = new Product(
					"El resplandor",
					17.99f,
					"Jack Torrance acepta cuidar un hotel aislado durante el invierno, pero las fuerzas que habitan el edificio amenazan a toda su familia.",
					"/files/14.jpg",
					0
			);

			Product libro15 = new Product(
					"It",
					21.99f,
					"Un grupo de amigos se enfrenta a una presencia aterradora que aparece en Derry y que parece regresar cada cierto tiempo.",
					"/files/15.jpg",
					0
			);

			Product libro16 = new Product(
					"El gran Gatsby",
					12.99f,
					"Nick Carraway conoce al enigmático Jay Gatsby y descubre el mundo de riqueza, fiestas y obsesiones que rodea su amor por Daisy.",
					"/files/16.jpg",
					0
			);

			Product libro17 = new Product(
					"Moby Dick",
					15.99f,
					"El marinero Ismael se embarca en el Pequod, cuyo capitán Ahab está obsesionado con dar caza a una enorme ballena blanca.",
					"/files/17.jpg",
					0
			);

			Product libro18 = new Product(
					"El guardián entre el centeno",
					13.99f,
					"Holden Caulfield relata varios días de su vida mientras intenta encontrar su lugar y comprender el mundo adulto.",
					"/files/18.jpg",
					0
			);

			Product libro19 = new Product(
					"El nombre del viento",
					21.99f,
					"Kvothe cuenta la historia de su vida, desde una infancia marcada por la música hasta sus primeros pasos en una escuela de magia.",
					"/files/19.jpg",
					0
			);

			Product libro20 = new Product(
					"El temor de un hombre sabio",
					23.99f,
					"Kvothe continúa su búsqueda de conocimiento y respuestas mientras se enfrenta a nuevos peligros, alianzas y decisiones.",
					"/files/20.jpg",
					0
			);

			Product libro21 = new Product(
					"El marciano",
					18.99f,
					"Tras quedar abandonado en Marte, el astronauta Mark Watney debe usar sus conocimientos científicos para sobrevivir y encontrar una forma de regresar.",
					"/files/21.jpg",
					0
			);

			Product libro22 = new Product(
					"El alquimista",
					14.99f,
					"Santiago, un joven pastor, emprende un viaje por el desierto en busca de un tesoro y descubre el valor de perseguir sus sueños.",
					"/files/22.jpg",
					0
			);

			Product libro23 = new Product(
					"Crimen y castigo",
					16.99f,
					"Rodion Raskólnikov comete un asesinato y se ve consumido por la culpa, el miedo y las consecuencias morales de sus actos.",
					"/files/23.jpg",
					0
			);

			Product libro24 = new Product(
					"Matar a un ruiseñor",
					15.99f,
					"Scout Finch observa cómo su padre, el abogado Atticus Finch, defiende a un hombre negro acusado injustamente en el sur de Estados Unidos.",
					"/files/24.jpg",
					0
			);

			Product libro25 = new Product(
					"El retrato de Dorian Gray",
					13.99f,
					"Dorian Gray conserva su juventud mientras un retrato refleja las huellas de sus decisiones y su degradación moral.",
					"/files/25.jpg",
					0
			);

			Product libro26 = new Product(
					"El conde de Montecristo",
					19.99f,
					"Edmond Dantès, víctima de una conspiración, escapa de prisión y prepara una compleja venganza contra quienes destruyeron su vida.",
					"/files/26.jpg",
					0
			);

			Product libro27 = new Product(
					"Los miserables",
					18.99f,
					"Jean Valjean intenta reconstruir su vida en una Francia marcada por la desigualdad, mientras el inspector Javert sigue sus pasos.",
					"/files/27.jpg",
					0
			);

			Product libro28 = new Product(
					"La Odisea",
					14.99f,
					"Odiseo afronta criaturas, dioses y numerosas pruebas durante su largo viaje de regreso a Ítaca tras la guerra de Troya.",
					"/files/28.jpg",
					0
			);

			Product libro29 = new Product(
					"Los juegos del hambre",
					17.99f,
					"Katniss Everdeen se ofrece como tributo en unos juegos televisados donde deberá luchar por sobrevivir y proteger a quienes ama.",
					"/files/29.jpg",
					0
			);

			Product libro30 = new Product(
					"En llamas",
					17.99f,
					"Katniss y Peeta regresan a la arena en una edición especial de los Juegos del Hambre, mientras crece la tensión contra el Capitolio.",
					"/files/30.jpg",
					0
			);

			Product libro31 = new Product(
					"El corredor del laberinto",
					16.99f,
					"Thomas despierta en un lugar cerrado por un enorme laberinto y trata de descubrir quién los ha llevado allí y cómo escapar.",
					"/files/31.jpg",
					0
			);

			Product libro32 = new Product(
					"Fundación",
					17.99f,
					"Hari Seldon desarrolla un plan para preservar el conocimiento humano y reducir el periodo de caos tras la caída de un imperio galáctico.",
					"/files/32.jpg",
					0
			);

			Product libro33 = new Product(
					"Yo, robot",
					15.99f,
					"Una colección de relatos explora los dilemas que surgen cuando los robots inteligentes interactúan con los seres humanos.",
					"/files/33.jpg",
					0
			);

			Product libro34 = new Product(
					"Neuromante",
					16.99f,
					"Un hacker recibe una peligrosa misión en un futuro dominado por corporaciones, redes informáticas y tecnología avanzada.",
					"/files/34.jpg",
					0
			);

			Product libro35 = new Product(
					"Fahrenheit 451",
					13.99f,
					"En una sociedad que prohíbe los libros, el bombero Guy Montag comienza a cuestionar el sistema que antes defendía.",
					"/files/35.jpg",
					0
			);

			Product libro36 = new Product(
					"Un mundo feliz",
					14.99f,
					"En una sociedad organizada alrededor del condicionamiento y el consumo, Bernard Marx empieza a cuestionar la felicidad impuesta.",
					"/files/36.jpg",
					0
			);

			Product libro37 = new Product(
					"La paciente silenciosa",
					16.99f,
					"Alicia Berenson deja de hablar después de un crimen, y un psicoterapeuta intenta descubrir qué ocurrió realmente.",
					"/files/37.jpg",
					0
			);

			Product libro38 = new Product(
					"Perdida",
					17.99f,
					"La desaparición de Amy Dunne convierte a su marido Nick en el centro de una investigación llena de sospechas y giros.",
					"/files/38.jpg",
					0
			);

			Product libro39 = new Product(
					"El código Da Vinci",
					18.99f,
					"Robert Langdon investiga una muerte en el Louvre y sigue una serie de pistas relacionadas con símbolos, obras de arte y secretos históricos.",
					"/files/39.jpg",
					0
			);

			Product libro40 = new Product(
					"American Gods",
					19.99f,
					"Shadow Moon se ve envuelto en un conflicto entre antiguas deidades y nuevos dioses surgidos de la sociedad moderna.",
					"/files/40.jpg",
					0
			);


// ============================================================
// INSERT PRODUCTS
// ============================================================

			List<Product> list = Arrays.asList(

					libro1,
					libro2,
					libro3,
					libro4,
					libro5,
					libro6,
					libro7,
					libro8,
					libro9,
					libro10,
					libro11,
					libro12,
					libro13,
					libro14,
					libro15,
					libro16,
					libro17,
					libro18,
					libro19,
					libro20,
					libro21,
					libro22,
					libro23,
					libro24,
					libro25,
					libro26,
					libro27,
					libro28,
					libro29,
					libro30,
					libro31,
					libro32,
					libro33,
					libro34,
					libro35,
					libro36,
					libro37,
					libro38,
					libro39,
					libro40

			);

			list.forEach(productService::insert);


// ============================================================
// STOCK
// ============================================================

			List<Stock> productStock = Arrays.asList(

					new Stock(libro1, "Librería Central", 2),
					new Stock(libro2, "Distribuidora Literaria", 4),
					new Stock(libro3, "Editorial Levante", 3),
					new Stock(libro4, "Almacén de Libros", 5),
					new Stock(libro5, "Librería Central", 1),
					new Stock(libro6, "Distribuidora Literaria", 6),
					new Stock(libro7, "Editorial Levante", 2),
					new Stock(libro8, "Almacén de Libros", 4),
					new Stock(libro9, "Librería Central", 3),
					new Stock(libro10, "Distribuidora Literaria", 5),
					new Stock(libro11, "Editorial Levante", 1),
					new Stock(libro12, "Almacén de Libros", 6),
					new Stock(libro13, "Librería Central", 2),
					new Stock(libro14, "Distribuidora Literaria", 4),
					new Stock(libro15, "Editorial Levante", 3),
					new Stock(libro16, "Almacén de Libros", 5),
					new Stock(libro17, "Librería Central", 1),
					new Stock(libro18, "Distribuidora Literaria", 6),
					new Stock(libro19, "Editorial Levante", 2),
					new Stock(libro20, "Almacén de Libros", 4),
					new Stock(libro21, "Librería Central", 3),
					new Stock(libro22, "Distribuidora Literaria", 5),
					new Stock(libro23, "Editorial Levante", 1),
					new Stock(libro24, "Almacén de Libros", 6),
					new Stock(libro25, "Librería Central", 2),
					new Stock(libro26, "Distribuidora Literaria", 4),
					new Stock(libro27, "Editorial Levante", 3),
					new Stock(libro28, "Almacén de Libros", 5),
					new Stock(libro29, "Librería Central", 1),
					new Stock(libro30, "Distribuidora Literaria", 6),
					new Stock(libro31, "Editorial Levante", 2),
					new Stock(libro32, "Almacén de Libros", 4),
					new Stock(libro33, "Librería Central", 3),
					new Stock(libro34, "Distribuidora Literaria", 5),
					new Stock(libro35, "Editorial Levante", 1),
					new Stock(libro36, "Almacén de Libros", 6),
					new Stock(libro37, "Librería Central", 2),
					new Stock(libro38, "Distribuidora Literaria", 4),
					new Stock(libro39, "Editorial Levante", 3),
					new Stock(libro40, "Almacén de Libros", 5)

			);

			productStock.forEach(stockService::insert);

		};
	}

}
