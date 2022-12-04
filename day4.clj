(ns day4)

(require '[clojure.string :as str])

(defn read-input [filepath]
    (-> filepath
          (slurp)
          (str/split #"\n")))

(defn parse-range [range]
    (let [split (str/split range #"-")]
        (map #(Integer/parseInt %) split)))

(defn parse-input [input]
    (->> input
         (map #(str/split % #","))
         (map #(identity [(parse-range (nth % 0)) (parse-range (nth % 1))]))))

(defn fully-contains [ranges]
    (let [first-range (ranges 0)
          second-range (ranges 1)
          c1 (nth first-range 0)
          f1 (nth first-range 1)
          c2 (nth second-range 0)
          f2 (nth second-range 1)]
          (or 
            (and
                (<= c1 c2)
                (>= f1 f2))
            (and
                (>= c1 c2)
                (<= f1 f2)))))

(defn overlap [ranges]
    (let [first-range (ranges 0)
          second-range (ranges 1)
          c1 (nth first-range 0)
          f1 (nth first-range 1)
          c2 (nth second-range 0)
          f2 (nth second-range 1)]
          (and
            (>= f1 c2)
            (>= f2 c1))))

(defn solve [input]
    (count (filter fully-contains input)))

(defn solve-part-2 [input]
    (count (filter overlap input)))

(def input (parse-input (read-input "input.txt")))
