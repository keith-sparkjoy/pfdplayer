(ns pfdplayer.data.pfd)

(defn sample-pfd
  []
  {:name "open-front-door"
   :aim "Open front door"
   :bboundary "You are standing outside the door."
   :eboundary "The door is open and you are standing inside."
   :steps [{:t :predicate
            :name "Key in hand?"
            :nva [{:t :directive
                   :name "Look in left pocket."}
                  {:t :predicate
                   :name "Key in hand?"}
                  {:t :directive
                   :name "Look in right pocket."}
                  {:t :predicate
                   :name "Key in hand?"}]}
           {:t :directive
            :name "Insert key in lock."}
           {:t :directive
            :name "Turn key counterclockwise."}
           {:t :directive
            :name "Turn key clockwise."}
           {:t :directive
            :name "Remove key from lock."}
           {:t :predicate
            :name "Door unlocked?"
            :nva [{:t :directive :name "Call a locksmith."}]}
           {:t :directive
            :name "Gently push open door."}
           {:t :directive
            :name "Step inside."}]})

