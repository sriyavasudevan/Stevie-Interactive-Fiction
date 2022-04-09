(define (domain monkey-banana-3)
	(:requirements :adl :domain-axioms :intentionality)
	(:types edible climbable rigid - thing
					lab - room
					monkey - player)
  (:constants BANANA - edible
  						STICK - thing
  						CHAIR - climbable
  						LAB - lab
  						PLAYER - monkey
  						HOOK - rigid)
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
		:parameters (?supporter - climbable ?player - player ?sameroom - room ?banana - edible)
		:precondition (and (contains ?sameroom ?supporter)
											 (contains ?supporter ?player)
											 (contains ?sameroom ?banana))
	  :effect (and (not (contains ?supporter ?player))
	  						 (contains ?sameroom ?supporter)
	  						 (contains ?sameroom ?player))
	)

  (:action wave-spec
    :parameters (?stick - thing ?player - player ?banana - edible
                 ?hook - rigid ?lab - room ?chair - climbable)
    :precondition (and (contains ?hook ?banana)
                       (contains ?player ?stick)
                       (contains ?chair ?player)
                       (contains ?lab ?chair)
                       (waveable ?stick)
                       (portable ?stick))
    :effect (and (not (contains ?hook ?banana))
                 (contains ?lab ?banana))
  )

  (:action eat
  	:parameters (?banana - edible ?player - player ?lab - room)
  	:precondition	(and (contains ?lab ?banana)
  										 (contains ?lab ?player))	
  	:effect (eaten ?player ?banana)
  )
)