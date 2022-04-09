(define (domain forest)
	(:requirements :adl :domain-axioms :intentionality)
	(:types edible climbable rigid - thing
					forest - room
					player - player)
  (:constants APPLE - edible
  						TWIG - thing
  						ROCK - climbable
  						FOREST - room
  						PLAYER - player
  						TREE - rigid)
	(:predicates (contains ?x - room ?y - thing)
							 (contains ?x - room ?y - player)
							 (contains ?x - player ?y - thing)
							 (contains ?x - thing ?y - player)
							 (contains ?x - thing ?y - thing)
							 (portable ?x - thing)
							 (waveable ?x - thing)
							 (eaten ?x - player ?y - edible))

	(:action take
	 	:parameters	(?player - player ?object - thing ?sameroom - room)
	 	:precondition (and (contains ?sameroom ?player)
	 										 (contains ?sameroom ?object)
	 										 (not (contains ?player ?object))
	 										 (portable ?object))
	 	:effect (contains ?player ?object)
	)

	(:action get-on
		:parameters (?supporter - climbable ?player - player ?sameroom - room)
		:precondition (and (contains ?sameroom ?supporter)
											 (contains ?sameroom ?player)
											 (not (contains ?supporter ?player)))
		:effect (and (not (contains ?sameroom ?player))
								 (contains ?supporter ?player))
	)

	(:action get-off
		:parameters (?supporter - climbable ?player - player ?sameroom - room ?food - edible)
		:precondition (and (contains ?sameroom ?supporter)
											 (contains ?supporter ?player)
											 (contains ?sameroom ?food))
	  :effect (and (not (contains ?supporter ?player))
	  						 (contains ?sameroom ?supporter)
	  						 (contains ?sameroom ?player))
	)

  (:action wave-spec
    :parameters (?stick - thing ?player - player ?food - edible
                 ?fixed - rigid ?room - room ?supporter - climbable)
    :precondition (and (contains ?fixed ?food)
                       (contains ?player ?stick)
                       (contains ?supporter ?player)
                       (contains ?room ?supporter)
                       (waveable ?stick)
                       (portable ?stick))
    :effect (and (not (contains ?fixed ?food))
                 (contains ?room ?food))
  )

  (:action eat
  	:parameters (?food - edible ?player - player ?room - room)
  	:precondition	(and (contains ?room ?food)
  										 (contains ?room ?player))	
  	:effect (eaten ?player ?food)
  )
)