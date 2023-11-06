(defn lazy-seq-filter [pred collection]
  (lazy-seq
    (when-let [s (seq collection)]
      (let [head (first s)
            tail (rest s)]
        (if (pred head)
          (cons head (lazy-seq-filter pred tail))
          (lazy-seq-filter pred tail))))))

(defn predicate-partition [pred coll]
  [(lazy-seq-filter pred coll) (lazy-seq-filter #(not (pred %)) coll)])

(let [[positive negative] (predicate-partition pos? (range -10 10))]

  (println "Числа больше 0:")
  (doseq [item positive]
    (print item " "))
  (println)

  (println "Числа меньше 0:")
  (doseq [item negative]
    (print item ", ")))