package com.cisco.pnda.examples.util;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Car extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4372457208513393133L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Car\",\"namespace\":\"com.cisco.pnda\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"speed\",\"type\":\"double\"},{\"name\":\"distance\",\"type\":\"double\"},{\"name\":\"timestamp\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public int id;
  @Deprecated public java.lang.Double speed;
  @Deprecated public java.lang.Double distance;
  @Deprecated public java.lang.Long timestamp;
  
  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Car() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   * @param id The new value for id
   * @param email The new value for email
   * @param phone The new value for phone
   */
  public Car(java.lang.Integer id, java.lang.Double speed, java.lang.Double distance, java.lang.Long timestamp) {
    this.id = id;
    this.speed = speed;
    this.distance = distance;
    this.timestamp = timestamp;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return speed;
    case 2: return distance;
    case 3: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: speed = (java.lang.Double)value$; break;
    case 2: distance = (java.lang.Double)value$; break;
    case 3: timestamp = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'speed' field.
   * @return The value of the 'speed' field.
   */
  public java.lang.Double getSpeed() {
    return speed;
  }

  /**
   * Sets the value of the 'speed' field.
   * @param value the value to set.
   */
  public void setSpeed(java.lang.Double value) {
    this.speed = value;
  }

  
  /**
   * Gets the value of the 'distance' field.
   * @return The value of the 'distance' field.
   */
  public java.lang.Double getDistance() {
    return distance;
  }

  /**
   * Sets the value of the 'distance' field.
   * @param value the value to set.
   */
  public void setDistance(java.lang.Double value) {
    this.distance = value;
  }

  /**
   * Gets the value of the 'time' field.
   * @return The value of the 'time' field.
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'time' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new Car RecordBuilder.
   * @return A new Car RecordBuilder
   */
  public static com.cisco.pnda.examples.util.Car.Builder newBuilder() {
    return new com.cisco.pnda.examples.util.Car.Builder();
  }

  /**
   * Creates a new Car RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Car RecordBuilder
   */
  public static com.cisco.pnda.examples.util.Car.Builder newBuilder(com.cisco.pnda.examples.util.Car.Builder other) {
    return new com.cisco.pnda.examples.util.Car.Builder(other);
  }

  /**
   * Creates a new Person RecordBuilder by copying an existing Person instance.
   * @param other The existing instance to copy.
   * @return A new Person RecordBuilder
   */
  public static com.cisco.pnda.examples.util.Car.Builder newBuilder(com.cisco.pnda.examples.util.Car other) {
    return new com.cisco.pnda.examples.util.Car.Builder(other);
  }

  /**
   * RecordBuilder for Car instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Car>
    implements org.apache.avro.data.RecordBuilder<Car> {

    private int id;
    private java.lang.Double speed;
    private java.lang.Double distance;
    private java.lang.Long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.cisco.pnda.examples.util.Car.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.speed)) {
        this.speed = data().deepCopy(fields()[1].schema(), other.speed);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.distance)) {
        this.distance = data().deepCopy(fields()[2].schema(), other.distance);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.timestamp)) {
          this.timestamp = data().deepCopy(fields()[3].schema(), other.timestamp);
          fieldSetFlags()[3] = true;
        }
    }

    /**
     * Creates a Builder by copying an existing Person instance
     * @param other The existing instance to copy.
     */
    private Builder(com.cisco.pnda.examples.util.Car other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.speed)) {
        this.speed = data().deepCopy(fields()[1].schema(), other.speed);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.distance)) {
        this.distance = data().deepCopy(fields()[2].schema(), other.distance);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.timestamp)) {
          this.timestamp = data().deepCopy(fields()[3].schema(), other.timestamp);
          fieldSetFlags()[3] = true;
        }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.cisco.pnda.examples.util.Car.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /**
     * Clears the value of the 'id' field.
     * @return This builder.
     */
   public com.cisco.pnda.examples.util.Car.Builder clearId() {
     fieldSetFlags()[0] = false;
     return this;
   }

   /**
    * Gets the value of the 'speed' field.
    * @return The value.
    */
    public java.lang.Double getSpeed() {
        return speed;
      }

      /**
        * Sets the value of the 'speed' field.
        * @param value The value of 'speed'.
        * @return This builder.
        */
      public com.cisco.pnda.examples.util.Car.Builder setSpeed(Double value) {
        validate(fields()[1], value);
        this.speed = value;
        fieldSetFlags()[0] = true;
        return this;
      }

      /**
        * Checks whether the 'speed' field has been set.
        * @return True if the 'speed' field has been set, false otherwise.
        */
      public boolean hasSpeed() {
        return fieldSetFlags()[1];
      }

      /**
       * Clears the value of the 'id' field.
       * @return This builder.
       */
     public com.cisco.pnda.examples.util.Car.Builder clearSpeed() {
       fieldSetFlags()[1] = false;
       return this;
     }

     /**
      * Gets the value of the 'distance' field.
      * @return The value.
      */
      public java.lang.Double getDistance() {
          return distance;
        }

        /**
          * Sets the value of the 'distance' field.
          * @param value The value of 'distance'.
          * @return This builder.
          */
        public com.cisco.pnda.examples.util.Car.Builder setDistance(Double value) {
          validate(fields()[2], value);
          this.speed = value;
          fieldSetFlags()[2] = true;
          return this;
        }

        /**
          * Checks whether the 'distance' field has been set.
          * @return True if the 'distance' field has been set, false otherwise.
          */
        public boolean hasDistance() {
          return fieldSetFlags()[2];
        }

        /**
         * Clears the value of the 'id' field.
         * @return This builder.
         */
       public com.cisco.pnda.examples.util.Car.Builder clearDistance() {
         fieldSetFlags()[2] = false;
         return this;
       }

       /**
        * Gets the value of the 'time' field.
        * @return The value.
        */
        public java.lang.Long getTimestamp() {
            return timestamp;
          }

          /**
            * Sets the value of the 'time' field.
            * @param value The value of 'time'.
            * @return This builder.
            */
          public com.cisco.pnda.examples.util.Car.Builder setTimestamp(Long value) {
            validate(fields()[3], value);
            this.timestamp = value;
            fieldSetFlags()[3] = true;
            return this;
          }

          /**
            * Checks whether the 'time' field has been set.
            * @return True if the 'time' field has been set, false otherwise.
            */
          public boolean hasTimestamp() {
            return fieldSetFlags()[3];
          }

          /**
           * Clears the value of the 'time' field.
           * @return This builder.
           */
         public com.cisco.pnda.examples.util.Car.Builder clearTimestamp() {
           fieldSetFlags()[3] = false;
           return this;
         }


    @Override
    public Car build() {
      try {
        Car record = new Car();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.speed = fieldSetFlags()[1] ? this.speed : (java.lang.Double) defaultValue(fields()[1]);
        record.distance = fieldSetFlags()[2] ? this.distance : (java.lang.Double) defaultValue(fields()[2]);
        record.timestamp = fieldSetFlags()[3] ? this.timestamp : (java.lang.Long) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
