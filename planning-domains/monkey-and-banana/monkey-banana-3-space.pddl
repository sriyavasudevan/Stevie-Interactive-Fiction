(define (space get-on-chair-and-wave-stick)
  (:literals (contains HOOK BANANA)
             (contains LAB CHAIR)
             (contains LAB HOOK)
             (contains LAB PLAYER)
             (contains LAB STICK)
             (portable BANANA)
             (portable CHAIR)
             (portable STICK)
             (waveable BANANA)
             (waveable CHAIR)
             (waveable STICK))
  (:steps (take PLAYER BANANA LAB)
          (take PLAYER STICK LAB)
          (take PLAYER CHAIR LAB)
          (get-on CHAIR PLAYER LAB)
          (get-off CHAIR PLAYER LAB BANANA)
          (wave-spec BANANA PLAYER BANANA HOOK LAB CHAIR)
          (wave-spec STICK PLAYER BANANA HOOK LAB CHAIR)
          (wave-spec CHAIR PLAYER BANANA HOOK LAB CHAIR)
          (eat BANANA PLAYER LAB))
  (:axioms))