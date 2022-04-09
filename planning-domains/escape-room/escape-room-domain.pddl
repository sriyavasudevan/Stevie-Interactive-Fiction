(define (domain escape-room)
	(:requirements :adl :domain-axioms :intentionality)
	(:types locked jumpable climbable - item
					room - room
					player - player)
	(:constants ROOM - room
							CUPBOARD - item
							STOOL - climbable
							DOOR - locked
							PLATFORM - jumpable
							CHEST - item
							KEY - item
							PLAYER - player)
	(:predicates (contains ?x - room ?y - item)
							 (contains ?x - room ?y - player)
							 (contains ?x - player ?y - item)
							 (contains ?x - item ?y - player)
							 (contains ?x - item ?y - item)
							 (portable ?x - item)
							 (can-open ?x - item)
							 (can-see ?x - item ?y - item)
							 (can-see ?x - item ?y - room))

	(:action take
	 	:parameters	(?player - player ?object - item ?sameroom - room)
	 	:precondition (or (and (contains ?sameroom ?object)
	 										     (contains ?sameroom ?player)
	 										     (not (contains ?player ?object))
	 										     (portable ?object))
	 								    (and (can-see ?object ?sameroom))
	 								         (portable ?object))
	 	:effect (and (contains ?player ?object)
	 							 (not (contains ?sameroom ?object)))
	)

	(:action open
		:parameters (?container - item ?contents - item ?sameroom - room ?player - player)
		:precondition (and (not (can-see ?contents ?sameroom))
											 (contains ?container ?contents)
											 (contains ?sameroom ?player)
											 (contains ?sameroom ?container)
											 (can-open ?container))
		:effect (and (contains ?sameroom ?contents)
								 (can-see ?contents ?sameroom))
	)

	(:action drop
		:parameters (?object - item ?sameroom - room ?player - player)
		:precondition (and (contains ?player ?object)
											 (portable ?object)
											 (contains ?sameroom ?player))
		:effect (and (contains ?sameroom ?object)
								 (not (contains ?player ?object))
								 (can-see ?object ?sameroom))
	)

	(:action get-on
		:parameters (?supporter - climbable ?player - player ?sameroom - room)
		:precondition (and (contains ?sameroom ?supporter)
											 (contains ?sameroom ?player)
											 (not (contains ?supporter ?player)))
		:effect (and (not (contains ?sameroom ?player))
								 (contains ?supporter ?player))
	)

	(:action jump-up
		:parameters (?player - player ?platform - jumpable ?sameroom - room ?supporter - climbable)
		:precondition (and (contains ?supporter ?platform)
											 (contains ?sameroom ?supporter)
											 (contains ?supporter ?player))
		:effect (contains ?platform ?player)
	)
)