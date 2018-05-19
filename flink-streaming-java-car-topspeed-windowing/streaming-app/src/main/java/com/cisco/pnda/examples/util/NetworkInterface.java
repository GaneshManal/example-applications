/**
  * Name:       Car
  * Purpose:    Generate the random data considering car as data source.
  * 			It generates car-id, speed, distance and time stamp periodically with specified sleep. 
  * Author:     PNDA team 
  *
  * Created:    24/05/2018
  */

package com.cisco.pnda.examples.util;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class NetworkInterface extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4372457208513393133L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NetworkInterface\",\"namespace\":\"com.cisco.pnda\",\"fields\":[{\"name\":\"hostname\",\"type\":\"string\"},{\"name\":\"nw_interface\",\"type\":\"string\"},{\"name\":\"RX_Bytes\",\"type\":\"long\"},{\"name\":\"RX_Dropped\",\"type\":\"int\"},{\"name\":\"RX_Errors\",\"type\":\"int\"},{\"name\":\"TX_Bytes\",\"type\":\"long\"},{\"name\":\"TX_Dropped\",\"type\":\"int\"},{\"name\":\"TX_Errors\",\"type\":\"int\"},{\"name\":\"timestamp\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String hostname;
  @Deprecated public java.lang.String nw_interface;
  
  @Deprecated public java.lang.Long rx_bytes;
  @Deprecated public java.lang.Integer rx_dropped;
  @Deprecated public java.lang.Integer rx_errors;
  
  @Deprecated public java.lang.Long tx_bytes;
  @Deprecated public java.lang.Integer tx_dropped;
  @Deprecated public java.lang.Integer tx_errors;
  
  @Deprecated public java.lang.Long timestamp;
  
  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NetworkInterface() {}

  /**
   * All-args constructor.
   * @param name The new value for name
   * @param id The new value for id
   * @param email The new value for email
   * @param phone The new value for phone
   */
  public NetworkInterface(java.lang.String hostname, java.lang.String nw_interface, java.lang.Long rx_bytes, java.lang.Integer rx_dropped, java.lang.Integer rx_errors, java.lang.Long tx_bytes, java.lang.Integer tx_dropped, java.lang.Integer tx_errors, java.lang.Long timestamp) {
    this.hostname = hostname;
    this.nw_interface = nw_interface;
    this.rx_bytes = rx_bytes;
    this.rx_dropped = rx_dropped;
    this.rx_errors = rx_errors;
    this.tx_bytes = tx_bytes;
    this.tx_dropped = tx_dropped;
    this.tx_errors = tx_errors;
    this.timestamp = timestamp;
    
    		
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return hostname;
    case 1: return nw_interface;
    case 2: return rx_bytes;
    case 3: return rx_dropped;
    case 4: return rx_errors;
    case 5: return tx_bytes;
    case 6: return tx_dropped;
    case 7: return tx_errors;
    case 8: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: hostname = (java.lang.String)value$.toString(); break;
    case 1: nw_interface = (java.lang.String)value$.toString(); break;
    case 2: rx_bytes = (java.lang.Long)value$; break;
    case 3: rx_dropped = (java.lang.Integer)value$; break;
    case 4: rx_errors = (java.lang.Integer)value$; break;
    case 5: tx_bytes = (java.lang.Long)value$; break;
    case 6: tx_dropped = (java.lang.Integer)value$; break;
    case 7: tx_errors = (java.lang.Integer)value$; break;
    case 8: timestamp = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'host-name' field.
   * @return The value of the 'host-name' field.
   */
  public java.lang.String getHostname() {
    return hostname;
  }

  /**
   * Sets the value of the 'host-name' field.
   * @param value the value to set.
   */
  public void setHostname(java.lang.String value) {
    this.hostname = value;
  }

  /**
   * Gets the value of the 'nw_interface' field.
   * @return The value of the 'nw_interface' field.
   */
  public java.lang.String getNwInterface() {
    return nw_interface;
  }

  /**
   * Sets the value of the 'nw_interface' field.
   * @param value the value to set.
   */
  public void setNwInterface(java.lang.String value) {
    this.nw_interface = value;
  }
  /**
   * Gets the value of the 'rx_bytes' field.
   * @return The value of the 'rx_bytes' field.
   */
  public java.lang.Long getRxBytes() {
    return rx_bytes;
  }

  /**
   * Sets the value of the 'rx_bytes' field.
   * @param value the value to set.
   */
  public void setRxBytes(java.lang.Long value) {
    this.rx_bytes = value;
  }

  /**
   * Gets the value of the 'rx_dropped' field.
   * @return The value of the 'rx_dropped' field.
   */
  public java.lang.Integer getRxDropped() {
    return rx_dropped;
  }

  /**
   * Sets the value of the 'rx_dropped' field.
   * @param value the value to set.
   */
  public void setRxDropped(java.lang.Integer value) {
    this.rx_dropped = value;
  }

  /**
  * Gets the value of the 'rx_errors' field.
  * @return The value of the 'rx_errors' field.
  */
 public java.lang.Integer getRxErrors() {
   return rx_errors;
 }

 /**
  * Sets the value of the 'rx_errors' field.
  * @param value the value to set.
  */
 public void setRxErrors(java.lang.Integer value) {
   this.rx_errors = value;
 }

  /**
   * Gets the value of the 'tx_bytes' field.
   * @return The value of the 'tx_bytes' field.
   */
  public java.lang.Long getTxBytes() {
    return tx_bytes;
  }

  /**
   * Sets the value of the 'tx_bytes' field.
   * @param value the value to set.
   */
  public void setTxBytes(java.lang.Long value) {
    this.tx_bytes = value;
  }

  /**
   * Gets the value of the 'tx_dropped' field.
   * @return The value of the 'tx_dropped' field.
   */
  public java.lang.Integer getTxDropped() {
    return tx_dropped;
  }

  /**
   * Sets the value of the 'tx_dropped' field.
   * @param value the value to set.
   */
  public void setTxDropped(java.lang.Integer value) {
    this.tx_dropped = value;
  }

  /**
   * Gets the value of the 'tx_errors' field.
   * @return The value of the 'tx_errors' field.
   */
  public java.lang.Integer getTxErrors() {
    return tx_errors;
  }

  /**
   * Sets the value of the 'tx_errors' field.
   * @param value the value to set.
   */
  public void setTxErrors(java.lang.Integer value) {
    this.tx_errors = value;
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
  public static com.cisco.pnda.examples.util.NetworkInterface.Builder newBuilder() {
    return new com.cisco.pnda.examples.util.NetworkInterface.Builder();
  }

  /**
   * Creates a new Car RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Car RecordBuilder
   */
  public static com.cisco.pnda.examples.util.NetworkInterface.Builder newBuilder(com.cisco.pnda.examples.util.NetworkInterface.Builder other) {
    return new com.cisco.pnda.examples.util.NetworkInterface.Builder(other);
  }

  /**
   * Creates a new Person RecordBuilder by copying an existing Person instance.
   * @param other The existing instance to copy.
   * @return A new Person RecordBuilder
   */
  public static com.cisco.pnda.examples.util.NetworkInterface.Builder newBuilder(com.cisco.pnda.examples.util.NetworkInterface other) {
    return new com.cisco.pnda.examples.util.NetworkInterface.Builder(other);
  }

  /**
   * RecordBuilder for Car instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NetworkInterface>
    implements org.apache.avro.data.RecordBuilder<NetworkInterface> {

	  private java.lang.String hostname;
	  private java.lang.String nw_interface;
	  private java.lang.Long rx_bytes;
	  private java.lang.Integer rx_dropped;
	  private java.lang.Integer rx_errors;
	  private java.lang.Long tx_bytes;
	  private java.lang.Integer tx_dropped;
	  private java.lang.Integer tx_errors;
	  private java.lang.Long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.cisco.pnda.examples.util.NetworkInterface.Builder other) {
      super(other);
	  	if (isValidValue(fields()[0], other.hostname)) {
	      this.hostname = data().deepCopy(fields()[0].schema(), other.hostname);
	      fieldSetFlags()[0] = true;
	    }
	    if (isValidValue(fields()[1], other.nw_interface)) {
	      this.nw_interface = data().deepCopy(fields()[1].schema(), other.nw_interface);
	      fieldSetFlags()[1] = true;
	    }
	    if (isValidValue(fields()[2], other.rx_bytes)) {
	      this.rx_bytes = data().deepCopy(fields()[2].schema(), other.rx_bytes);
	      fieldSetFlags()[2] = true;
	    }
	    if (isValidValue(fields()[3], other.rx_dropped)) {
	      this.rx_dropped = data().deepCopy(fields()[3].schema(), other.rx_dropped);
	      fieldSetFlags()[3] = true;
	    }
	    if (isValidValue(fields()[4], other.rx_errors)) {
	      this.rx_errors = data().deepCopy(fields()[4].schema(), other.rx_errors);
	      fieldSetFlags()[4] = true;
	    }
	    if (isValidValue(fields()[5], other.tx_bytes)) {
	      this.tx_bytes = data().deepCopy(fields()[5].schema(), other.tx_bytes);
	      fieldSetFlags()[5] = true;
	    }
	    if (isValidValue(fields()[6], other.tx_dropped)) {
	      this.tx_dropped = data().deepCopy(fields()[6].schema(), other.tx_dropped);
	      fieldSetFlags()[6] = true;
	    }
	    if (isValidValue(fields()[7], other.tx_errors)) {
	      this.tx_errors = data().deepCopy(fields()[7].schema(), other.tx_errors);
	      fieldSetFlags()[7] = true;
	    }
	    if (isValidValue(fields()[8], other.timestamp)) {
	      this.timestamp = data().deepCopy(fields()[8].schema(), other.timestamp);
	      fieldSetFlags()[8] = true;
	    }
    }

    /**
     * Creates a Builder by copying an existing Person instance
     * @param other The existing instance to copy.
     */
    private Builder(com.cisco.pnda.examples.util.NetworkInterface other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.hostname)) {
          this.hostname = data().deepCopy(fields()[0].schema(), other.hostname);
          fieldSetFlags()[0] = true;
        }
        if (isValidValue(fields()[1], other.nw_interface)) {
          this.nw_interface = data().deepCopy(fields()[1].schema(), other.nw_interface);
          fieldSetFlags()[1] = true;
        }
        if (isValidValue(fields()[2], other.rx_bytes)) {
          this.rx_bytes = data().deepCopy(fields()[2].schema(), other.rx_bytes);
          fieldSetFlags()[2] = true;
        }
        if (isValidValue(fields()[3], other.rx_dropped)) {
          this.rx_dropped = data().deepCopy(fields()[3].schema(), other.rx_dropped);
          fieldSetFlags()[3] = true;
        }
        if (isValidValue(fields()[4], other.rx_errors)) {
          this.rx_errors = data().deepCopy(fields()[4].schema(), other.rx_errors);
          fieldSetFlags()[4] = true;
        }
        if (isValidValue(fields()[5], other.tx_bytes)) {
          this.tx_bytes = data().deepCopy(fields()[5].schema(), other.tx_bytes);
          fieldSetFlags()[5] = true;
        }
        if (isValidValue(fields()[6], other.tx_dropped)) {
          this.tx_dropped = data().deepCopy(fields()[6].schema(), other.tx_dropped);
          fieldSetFlags()[6] = true;
        }
        if (isValidValue(fields()[7], other.tx_errors)) {
          this.tx_errors = data().deepCopy(fields()[7].schema(), other.tx_errors);
          fieldSetFlags()[7] = true;
        }
        if (isValidValue(fields()[8], other.timestamp)) {
          this.timestamp = data().deepCopy(fields()[8].schema(), other.timestamp);
          fieldSetFlags()[8] = true;
        }
    }

   /**
    * Gets the value of the 'host-name' field.
    * @return The value.
    */
   	public java.lang.String getHostname() {
   		return hostname;
   	}

  /**
    * Sets the value of the 'host-name' field.
    * @param value The value of 'host-name'.
    * @return This builder.
    */
   	public com.cisco.pnda.examples.util.NetworkInterface.Builder setHostname(String value) {
   		validate(fields()[0], value);
   		this.hostname = value;
   		fieldSetFlags()[0] = true;
   		return this;
   	}

  /**
    * Checks whether the 'host-name' field has been set.
    * @return True if the 'host-name' field has been set, false otherwise.
    */
   	public boolean hasHostname() {
   		return fieldSetFlags()[0];
   	}
  
  /**
   * Clears the value of the 'host-name' field.
   * @return This builder.
   */
  	public com.cisco.pnda.examples.util.NetworkInterface.Builder clearHostname() {
  		fieldSetFlags()[0] = false;
  		return this;
  	}
  	
  	/**
	 * Gets the value of the 'nw_interface' field.
	 * @return The value.
	 */
	 public java.lang.String getNwInterface() {
	   return nw_interface;
	 }
	
	/**
	 * Sets the value of the 'nw_interface' field.
	 * @param value The value of 'nw_interface'.
	 * @return This builder.
	 */
	 public com.cisco.pnda.examples.util.NetworkInterface.Builder setNwInterface(String value) {
	   validate(fields()[1], value);
	   this.nw_interface = value;
	   fieldSetFlags()[1] = true;
	   return this;
	 }
	
	/**
	 * Checks whether the 'nw_interface' field has been set.
	 * @return True if the 'nw_interface' field has been set, false otherwise.
	 */
	 public boolean hasNwInterface() {
	   return fieldSetFlags()[1];
	 }
	
	/**
	* Clears the value of the 'nw_interface' field.
	* @return This builder.
	*/
	 public com.cisco.pnda.examples.util.NetworkInterface.Builder clearNwInterface() {
	   fieldSetFlags()[1] = false;
	   return this;
	 }

     /**
	  * Gets the value of the 'rx_bytes' field.
	  * @return The value.
	  */
	  public java.lang.Long getRxBytes() {
	    return rx_bytes;
	  }
	
	/**
	  * Sets the value of the 'rx_bytes' field.
	  * @param value The value of 'rx_bytes'.
	  * @return This builder.
	  */
	  public com.cisco.pnda.examples.util.NetworkInterface.Builder setRxBytes(Long value) {
	    validate(fields()[2], value);
	    this.rx_bytes = value;
	    fieldSetFlags()[2] = true;
	    return this;
	  }
	
	/**
	  * Checks whether the 'rx_bytes' field has been set.
	  * @return True if the 'rx_bytes' field has been set, false otherwise.
	  */
	  public boolean hasRxBytes() {
	    return fieldSetFlags()[2];
	  }
	
	/**
	 * Clears the value of the 'rx_bytes' field.
	 * @return This builder.
	 */
	  public com.cisco.pnda.examples.util.NetworkInterface.Builder clearRxBytes() {
	    fieldSetFlags()[2] = false;
	    return this;
	  }

   /**
    * Gets the value of the 'rx_dropped' field.
    * @return The value.
    */
	  public java.lang.Integer getRxDropped() {
		return rx_dropped;
   }

/**
    * Sets the value of the 'rx_dropped' field.
    * @param value The value of 'rx_dropped'.
    * @return This builder.
    */
	  public com.cisco.pnda.examples.util.NetworkInterface.Builder setRxDropped(int value) {
		  validate(fields()[3], value);
		  this.rx_dropped = value;
		  fieldSetFlags()[3] = true;
		  return this;
   }

 /**
   * Checks whether the 'rx_dropped' field has been set.
   * @return True if the 'rx_dropped' field has been set, false otherwise.
   */
   public boolean hasRxDropped() {
     return fieldSetFlags()[3];
   }
 
 /**
   * Clears the value of the 'rx_dropped' field.
   * @return This builder.
   */
   public com.cisco.pnda.examples.util.NetworkInterface.Builder clearRxDropped() {
     fieldSetFlags()[3] = false;
     return this;
   }
   /**
	* Gets the value of the 'rx_errors' field.
	* @return The value.
	*/
	public java.lang.Integer getRxErrors() {
	  return rx_errors;
	}
	
	/**
	* Sets the value of the 'rx_errors' field.
	* @param value The value of 'rx_errors'.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder setRxErrors(int value) {
	  validate(fields()[4], value);
	  this.rx_errors = value;
	  fieldSetFlags()[4] = true;
	  return this;
	}
	
	/**
	* Checks whether the 'rx_errors' field has been set.
	* @return True if the 'rx_errors' field has been set, false otherwise.
	*/
	public boolean hasRxErrors() {
	  return fieldSetFlags()[4];
	}
	
	/**
	* Clears the value of the 'rx_errors' field.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder clearRxErrors() {
	  fieldSetFlags()[4] = false;
	  return this;
	}
	
	/**
	* Gets the value of the 'tx_bytes' field.
	* @return The value.
	*/
	public java.lang.Long getTxBytes() {
	 return tx_bytes;
	}
	
	/**
	* Sets the value of the 'tx_bytes' field.
	* @param value The value of 'tx_bytes'.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder setTxBytes(Long value) {
	 validate(fields()[5], value);
	 this.tx_bytes = value;
	 fieldSetFlags()[5] = true;
	 return this;
	}
	
	/**
	* Checks whether the 'tx_bytes' field has been set.
	* @return True if the 'tx_bytes' field has been set, false otherwise.
	*/
	public boolean hasTxBytes() {
	 return fieldSetFlags()[5];
	}
	
	/**
	* Clears the value of the 'tx_bytes' field.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder clearTxBytes() {
	 fieldSetFlags()[5] = false;
	 return this;
	}
	
	
	/**
	* Gets the value of the 'tx_dropped' field.
	* @return The value.
	*/
	public java.lang.Integer getTxDropped() {
	return tx_dropped;
	}
	
	/**
	* Sets the value of the 'tx_dropped' field.
	* @param value The value of 'tx_dropped'.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder setTxDropped(int value) {
	validate(fields()[6], value);
	this.tx_dropped = value;
	fieldSetFlags()[6] = true;
	return this;
	}
	
	/**
	* Checks whether the 'tx_dropped' field has been set.
	* @return True if the 'tx_dropped' field has been set, false otherwise.
	*/
	public boolean hasTxDropped() {
	return fieldSetFlags()[6];
	}
	
	/**
	* Clears the value of the 'tx_dropped' field.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder clearTxDropped() {
	fieldSetFlags()[6] = false;
	return this;
	}
	
	/**
	* Gets the value of the 'tx_errors' field.
	* @return The value.
	*/
	public java.lang.Integer getTxErrors() {
	return tx_errors;
	}
	
	/**
	* Sets the value of the 'tx_errors' field.
	* @param value The value of 'tx_errors'.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder setTxErrors(int value) {
	validate(fields()[7], value);
	this.tx_errors = value;
	fieldSetFlags()[7] = true;
	return this;
	}
	
	/**
	* Checks whether the 'tx_errors' field has been set.
	* @return True if the 'tx_errors' field has been set, false otherwise.
	*/
	public boolean hasTxErrors() {
	return fieldSetFlags()[7];
	}
	
	/**
	* Clears the value of the 'tx_errors' field.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder clearTxErrors() {
	fieldSetFlags()[7] = false;
	return this;
	}
	/**
	* Gets the value of the 'time-stamp' field.
	* @return The value.
	*/
	public java.lang.Long getTimestamp() {
	 return timestamp;
	}
	
	/**
	* Sets the value of the 'time-stamp' field.
	* @param value The value of 'time-stamp'.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder setTimestamp(Long value) {
	 validate(fields()[8], value);
	 this.timestamp = value;
	 fieldSetFlags()[8] = true;
	 return this;
	}
	
	/**
	* Checks whether the 'time-stamp' field has been set.
	* @return True if the 'time-stamp' field has been set, false otherwise.
	*/
	public boolean hasTimestamp() {
	 return fieldSetFlags()[8];
	}
	
	/**
	* Clears the value of the 'time-stamp' field.
	* @return This builder.
	*/
	public com.cisco.pnda.examples.util.NetworkInterface.Builder clearTimestamp() {
	 fieldSetFlags()[8] = false;
	 return this;
	}

    @Override
    public NetworkInterface build() {
      try {
        NetworkInterface record = new NetworkInterface();
        record.hostname = fieldSetFlags()[0] ? this.hostname : (java.lang.String) defaultValue(fields()[0]);
        record.nw_interface = fieldSetFlags()[1] ? this.nw_interface : (java.lang.String) defaultValue(fields()[1]);
        record.rx_bytes = fieldSetFlags()[2] ? this.rx_bytes : (java.lang.Long) defaultValue(fields()[2]);
        record.rx_dropped = fieldSetFlags()[3] ? this.rx_dropped : (java.lang.Integer) defaultValue(fields()[3]);
        record.rx_errors = fieldSetFlags()[4] ? this.rx_errors : (java.lang.Integer) defaultValue(fields()[4]);
        record.tx_bytes = fieldSetFlags()[5] ? this.rx_bytes : (java.lang.Long) defaultValue(fields()[5]);
        record.tx_dropped = fieldSetFlags()[6] ? this.tx_dropped : (java.lang.Integer) defaultValue(fields()[6]);
        record.tx_errors = fieldSetFlags()[7] ? this.tx_errors : (java.lang.Integer) defaultValue(fields()[7]);
        record.timestamp = fieldSetFlags()[8] ? this.timestamp : (java.lang.Long) defaultValue(fields()[8]);
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
