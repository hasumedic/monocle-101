package com.example

import monocle.function.Each
import monocle.{Traversal, macros}
import monocle.macros.GenLens
import monocle.function.all._
import monocle.std.set._
import scalaz.Applicative
import scalaz.std.set._

object Monocle extends App {

  def setTraversal[A]: Traversal[Set[A], A] = new Traversal[Set[A], A]{
    override def modifyF[F[_]](f: (A) => F[A])(s: Set[A])(implicit F: Applicative[F]): F[Set[A]] =
      s.foldRight(F.pure(Set.empty[A]))((a, fsa) => F.apply2(fsa, f(a))(_ + _))
  }

  case class School(name: String, address: Address, classes: Set[ClassRoom])

  case class Address(street: String, postcode: String, city: String)

  case class ClassRoom(number: Int, tables: Int, chairs: Int)

  val mySchool = School(
    "Joan Miró", Address("Diputació 21", "08015", "Barcelona"), Set(
      ClassRoom(1, 10, 20), ClassRoom(2, 5, 10), ClassRoom(3, 1, 1)
    )
  )

  val _tables = GenLens[ClassRoom](_.tables)
  val _classes = GenLens[School](_.classes)

  println((_classes composeTraversal setTraversal composeLens _tables).modify(_ + 5)(mySchool))


//  val _classes = Lens[School, Set[ClassRoom]](_.classes)(s => e => e.copy(classes = s))
//  val _tables = Lens[ClassRoom, Int](_.tables)(t => e => e.copy(tables = t))
//
//  (_classes composeLens _tables).modify(_ + 1)(mySchool)


}
